package com.example.devops.demo.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class Controller {


    @GetMapping("/")
    public String homepage() {
        return "<h1>Hello World!!!</h1>";
    }
}


