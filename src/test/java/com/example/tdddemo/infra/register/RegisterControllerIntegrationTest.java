package com.example.tdddemo.infra.register;

import com.example.tdddemo.domain.entites.User;
import com.example.tdddemo.domain.register.RegisterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegisterRepository registerRepository;

    @Test
    public void testRegisterNewUser() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("email", "hung@gmail.com");
        request.put("mobile", "0123456789");
        request.put("password", "123456");

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("hung@gmail.com"))
                .andExpect(jsonPath("mobile").value("0123456789"))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("createdAt").exists());

        User registerUser = registerRepository.findByEmailOrMobile("hung@gmail.com", "");
        assertNotNull(registerUser);
        assertEquals("hung@gmail.com", registerUser.getEmail());
        assertEquals("0123456789", registerUser.getMobile());
        assertNotEquals("123456", registerUser.getPassword());
    }
}