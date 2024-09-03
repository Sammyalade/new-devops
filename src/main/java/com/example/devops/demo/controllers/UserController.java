package com.example.devops.demo.controllers;
import com.example.devops.demo.dtos.requests.RegisterRequest;
import com.example.devops.demo.dtos.responses.RegisterUserResponse;
import com.example.devops.demo.services.interfaces.UserInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserInterface userService;

        @PostMapping("/register")
        public ResponseEntity<RegisterUserResponse> registerUser(@RequestParam RegisterRequest registerRequest) {
            try {
                RegisterUserResponse response = userService.register(registerRequest);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (IllegalArgumentException error) {
                RegisterUserResponse errorResponse = new RegisterUserResponse();
                errorResponse.setMessage(error.getMessage());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        }
    }


