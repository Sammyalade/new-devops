package com.example.devops.demo.services.impls;

import com.example.devops.demo.dtos.requests.LoginRequest;
import com.example.devops.demo.dtos.requests.RegisterRequest;
import com.example.devops.demo.dtos.responses.LoginResponse;
import com.example.devops.demo.dtos.responses.RegisterUserResponse;
import com.example.devops.demo.model.User;
import com.example.devops.demo.repositories.UserRepository;
import com.example.devops.demo.services.interfaces.UserInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DemoUserService implements UserInterface {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DemoUserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public RegisterUserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = modelMapper.map(request, User.class);
        user =  userRepository.save(user);
        var response = modelMapper.map(user, RegisterUserResponse.class);
        response.setMessage("registered successfully");
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = findUserByEmail(request.getEmail());
        checkForNullUser(user);
        checkUserPassword(request, user);
        return modelMapper.map(user, LoginResponse.class);
    }

    private static void checkUserPassword(LoginRequest request, User user) {
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    private static void checkForNullUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email not found")
        );
    }
}

