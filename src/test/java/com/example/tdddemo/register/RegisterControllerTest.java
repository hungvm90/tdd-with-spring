package com.example.tdddemo.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterController.class)
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRegisterNewUser_whenSubmitBothValidEmailAndMobile_shouldAllowRegister() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("email", "hung@gmail.com");
        request.put("mobile", "0123456789");
        request.put("password", "123456");
        User user = new User();
        user.setEmail("hung@gmail.com");
        user.setMobile("0123456789");
        user.setPassword("123456");
        user.setId(1);
        user.setCreatedAt(new Date());
        given(registerService.register(any())).willReturn(user);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("email").value(user.getEmail()))
                .andExpect(jsonPath("mobile").value(user.getMobile()))
                .andExpect(jsonPath("password").doesNotExist());

    }
}