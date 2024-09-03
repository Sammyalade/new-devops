package com.example.devops.demo.dtos.responses;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserResponse {
    private Long id;
    private String name;
    private String message;
}
