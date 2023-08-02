package com.codecool.trv.dto.note;

import com.codecool.trv.dto.user.UserResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdateNoteResponse(String text, LocalDateTime updatedAt, UserResponse updatedBy) {
}
