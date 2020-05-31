package com.example.tdddemo.infra.register;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String mobile;
    private String password;
}

