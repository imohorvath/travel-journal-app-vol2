package com.codecool.trv.mapper;

import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NewNoteResponse;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.model.Journal;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.NoteImage;
import com.codecool.trv.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class NoteMapper {

    public static NoteResponse mapToNoteResponse(Note note, List<String> imageBase64List) {
        return NoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdBy(UserMapper.mapToUserResponse(note.getCreatedBy()))
                .geoIP(note.getGeoIP())
                .journalId(note.getJournal().getId())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(UserMapper.mapToUserResponse(note.getUpdatedBy()))
                .imageLinks(note.getImageLinks().stream().map(NoteImage::getUrl).collect(Collectors.toSet()))
                .imageBytesList(imageBase64List)
                .build();
    }

    public static NewNoteResponse mapToNewNoteResponse(Note note, List<String> imageBase64List) {
        return NewNoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdBy(UserMapper.mapToUserResponse(note.getCreatedBy()))
                .geoIP(note.getGeoIP())
                .journalId(note.getJournal().getId())
                .updatedAt(note.getUpdatedAt())
                .updatedBy(UserMapper.mapToUserResponse(note.getUpdatedBy()))
                .imageLinks(note.getImageLinks().stream().map(NoteImage::getUrl).collect(Collectors.toSet()))
                .imageBytesList(imageBase64List)
                .build();
    }

    public static Note mapToNote(Journal journal, User creator, String noteText) {
        return Note.builder()
                .text(noteText)
                .createdBy(creator)
                .updatedBy(creator)
                .journal(journal)
                .build();
    }


}
