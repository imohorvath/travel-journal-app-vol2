package com.codecool.trv.service;

import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.note.UpdateNoteResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.mapper.NoteMapper;
import com.codecool.trv.model.Journal;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.NoteImage;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.NoteRepository;
import com.codecool.trv.validation.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteImageService noteImageService;


    @Autowired
    public NoteService(NoteRepository noteRepository, NoteImageService noteImageService) {
        this.noteRepository = noteRepository;
        this.noteImageService = noteImageService;
    }

    Note findNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note is not found with id: " + noteId));
    }

    public NoteResponse findNoteResponseById(Long noteId) {
        Note note = findNoteById(noteId);
        List<String> imageBase64List = getImageBase64ListByNoteId(noteId);
        return NoteMapper.mapToNoteResponse(note, imageBase64List);
    }

    public UpdateNoteResponse updateNoteById(Long noteId) {
        //TODO
        return null;
    }

    public void deleteNoteById(Long noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new ResourceNotFoundException("Note not found");
        }
        noteImageService.deleteNoteImagesByNoteId(noteId);
        noteRepository.deleteById(noteId);
    }

    public List<NoteResponse> findAllNotesByJournalId(Long journalId) {
        return noteRepository.findAllByJournal_Id(journalId)
                .stream()
                .map(note -> {
                    List<String> imageBase64List = getImageBase64ListByNoteId(note.getId());
                    return NoteMapper.mapToNoteResponse(note, imageBase64List);
                }).toList();
    }

    public void deleteAllNotesByJournalId(Long journalId) {
        noteRepository.deleteAllByJournal_Id(journalId);
    }

    public void deleteAllNotesByUserId(Long userId) {
        noteRepository.deleteNotesByCreatedBy_IdOrUpdatedBy_Id(userId, userId);
    }

    public Note addNote(Journal journal, User creator, String noteText, MultipartFile[] files) {
        Note savedNote = noteRepository.save(NoteMapper.mapToNote(journal, creator, noteText));

        if (files != null) {
            Arrays.asList(files).forEach(multipartFile -> {
                try {
                    NoteImage savedImage = noteImageService.save(multipartFile, savedNote);
                    savedNote.addImage(savedImage);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            });
        }

        return savedNote;
    }

    public List<byte[]> getImageBytesListByNoteId(Long noteId) {
        Note note = findNoteById(noteId);
        List<byte[]> imageBytesList = new ArrayList<>();

        if (note != null) {
            imageBytesList = note.getImageLinks().stream()
                    .map(image -> {
                        try {
                            return noteImageService.loadImageBytes(noteId, image.getUrl());
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }

        return imageBytesList;
    }

    public List<String> getImageBase64ListByNoteId(Long noteId) {
        Note note = findNoteById(noteId);
        List<String> imageBase64List = new ArrayList<>();

        if (note != null) {
            imageBase64List = note.getImageLinks().stream()
                    .map(image -> {
                        try {
                            return Base64.getEncoder().encodeToString(noteImageService.loadImageBytes(noteId, image.getUrl()));
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }

        return imageBase64List;
    }

}
