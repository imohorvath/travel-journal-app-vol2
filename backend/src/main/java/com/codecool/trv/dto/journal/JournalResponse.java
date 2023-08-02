package com.codecool.trv.dto.journal;

import com.codecool.trv.dto.user.UserResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record JournalResponse(Long id, String title, LocalDateTime createdAt, Set<UserResponse> contributors) {
}
