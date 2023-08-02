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
                .createdBy(note.getCreatedBy())
                .geoIP(note.getGeoIP())
                .journal(note.getJournal())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(note.getUpdatedBy())
                .build();
    }

    public static NewNoteResponse mapToNewNoteResponse(Note note) {
        return NewNoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdBy(note.getCreatedBy().getUsername())
                .journalTitle(note.getJournal().getTitle())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(note.getUpdatedBy().getUsername())
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
