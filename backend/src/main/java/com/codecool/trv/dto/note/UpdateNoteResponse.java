package com.codecool.trv.dto.note;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdateNoteResponse(LocalDateTime updatedAt, String text) {
}
