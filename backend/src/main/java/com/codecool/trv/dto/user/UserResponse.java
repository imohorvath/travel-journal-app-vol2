package com.codecool.trv.dto.user;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String username) {
}
