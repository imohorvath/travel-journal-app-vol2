package com.codecool.trv.dto.note;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NewNoteResponse(Long id, String text, LocalDateTime createdAt, String createdBy, String journalTitle, LocalDateTime updatedAt, String updatedBy) {
}
