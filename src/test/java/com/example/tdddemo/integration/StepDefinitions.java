package com.example.tdddemo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefinitions {
    private String email;
    private String mobile;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private ResultActions resultActions;

    @Then("I sign up success with successful response")
    public void iSignUpSuccessWithSuccessfulResponse() throws Exception {
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("mobile").value(mobile))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("createdAt").exists());
    }

    @When("I sign up with {string}, {string} and {string}")
    public void iSignUpWithValidEmailMobileAndPassword(String email, String mobile, String password) throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("email", email);
        request.put("mobile", mobile);
        request.put("password", password);
        this.email = email;
        this.mobile = mobile;
        resultActions = mockMvc
                .perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

    }

    @Then("I sign up fail with error response")
    public void iSignUpFailWithErrorResponse() throws Exception {
        resultActions.andExpect(status().isConflict())
                .andExpect(jsonPath("code").value("RESOURCE_ALREADY_EXIST"));
    }
}