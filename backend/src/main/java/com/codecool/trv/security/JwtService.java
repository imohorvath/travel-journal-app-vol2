package com.codecool.trv.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String extractUsername(String jwt) {
        //TODO
    return null;
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        //TODO
        return false;
    }
}
