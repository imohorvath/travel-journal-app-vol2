package com.codecool.trv.dto.user;

public record UpdateUserRequest(String username, String firstName, String lastName, String email, String password)  {
}
