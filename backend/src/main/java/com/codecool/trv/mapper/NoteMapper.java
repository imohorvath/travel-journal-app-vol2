package com.codecool.trv.mapper;

import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NewNoteResponse;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.model.Journal;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.User;

public class NoteMapper {

    public static NoteResponse mapToNoteResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdBy(UserMapper.mapToUserResponse(note.getCreatedBy()))
                .geoIP(note.getGeoIP())
                .journalId(note.getJournal().getId())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(UserMapper.mapToUserResponse(note.getUpdatedBy()))
                .build();
    }

    public static NewNoteResponse mapToNewNoteResponse(Note note) {
        return NewNoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdBy(UserMapper.mapToUserResponse(note.getCreatedBy()))
                .geoIP(note.getGeoIP())
                .journalId(note.getJournal().getId())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(UserMapper.mapToUserResponse(note.getUpdatedBy()))
                .build();
    }

    public static Note mapToNote(Journal journal, User creator, NewNoteRequest newNoteRequest) {
        return Note.builder()
                .text(newNoteRequest.text())
                .createdBy(creator)
                .updatedBy(creator)
                .journal(journal)
                .build();
    }


}
