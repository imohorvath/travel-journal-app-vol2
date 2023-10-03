package com.codecool.trv.security.service;

import com.codecool.trv.security.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitService {

    private final AuthenticationService authenticationService;

    @Autowired
    public DataInitService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void seedDatabase() {

        RegisterRequest defaultUser = RegisterRequest
                .builder()
                .username("testuser")
                .firstName("test")
                .lastName("test")
                .email("testuser@gmail.com")
                .password("Test!2345")
                .build();

        authenticationService.register(defaultUser);
    }

}
