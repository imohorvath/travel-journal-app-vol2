package com.codecool.trv.dto.note;

import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.model.GeoIP;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NewNoteResponse(Long id, String text, LocalDateTime createdAt, UserResponse createdBy, GeoIP geoIP, Long journalId, LocalDateTime updatedAt, UserResponse updatedBy) {
}
