package com.codecool.trv.security.model;

public record RegisterRequest(String username, String firstName, String lastName, String email, String password) {
}
