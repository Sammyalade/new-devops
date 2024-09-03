package com.example.devops.demo.dtos.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String email;
    private String password;
}
