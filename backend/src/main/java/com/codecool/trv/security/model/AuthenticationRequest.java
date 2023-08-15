package com.codecool.trv.security.model;


import lombok.Builder;

@Builder
public record AuthenticationRequest(String email, String password) {

}
