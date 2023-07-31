package com.codecool.trv.dto.user;

public record NewUserRequest(String username, String firstName, String lastName, String email, String password) {
}
