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

import java.util.Arrays;
import java.util.List;

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
        return noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note is not found with id: " + noteId));
    }

    public NoteResponse findNoteResponseById(Long noteId) {
        return NoteMapper.mapToNoteResponse(findNoteById(noteId));
    }

    public UpdateNoteResponse updateNoteById(Long noteId) {
        //TODO
        return null;
    }

    public void deleteNoteById(Long noteId) {
        if(!noteRepository.existsById(noteId)) {
            throw new ResourceNotFoundException("Note not found");
        }
        noteImageService.deleteNoteImagesByNoteId(noteId);
        noteRepository.deleteById(noteId);
    }

    public List<NoteResponse> findAllNotesByJournalId(Long journalId) {
        return noteRepository.findAllByJournal_Id(journalId).stream().map(NoteMapper::mapToNoteResponse).toList();
    }

    public void deleteAllNotesByJournalId(Long journalId) {
        noteRepository.deleteAllByJournal_Id(journalId);
    }

    public void deleteAllNotesByUserId(Long userId) {
        noteRepository.deleteNotesByCreatedBy_IdOrUpdatedBy_Id(userId, userId);
    }

    public Note addNote(Journal journal, User creator, String noteText, MultipartFile[] files) {
        Note savedNote = noteRepository.save(NoteMapper.mapToNote(journal, creator, noteText));

        if(files != null) {
            Arrays.asList(files).forEach(multipartFile -> {
                try{
                    NoteImage savedImage = noteImageService.save(multipartFile, savedNote);
                    savedNote.addImage(savedImage);
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            });
        }

        return savedNote;
    }

}
