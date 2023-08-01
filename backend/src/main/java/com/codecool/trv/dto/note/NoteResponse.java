package com.codecool.trv.dto.note;

import com.codecool.trv.model.GeoIP;
import com.codecool.trv.model.Journal;
import com.codecool.trv.model.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NoteResponse(Long id, String text, LocalDateTime createdAt, User createdBy, GeoIP geoIP, Journal journal, LocalDateTime updatedAt, User updatedBy) {
}
