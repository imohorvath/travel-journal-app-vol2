package com.codecool.trv.service;

import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.note.UpdateNoteResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.mapper.NoteMapper;
import com.codecool.trv.model.Journal;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserService userService;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
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
        noteRepository.deleteById(noteId);
    }

    public List<NoteResponse> findAllNotesByJournalId(Long journalId) {
        return noteRepository.findAllByJournal_Id(journalId).stream().map(NoteMapper::mapToNoteResponse).toList();
    }

    public void deleteAllNotesByJournalId(Long journalId) {
        noteRepository.deleteAllByJournal_Id(journalId);
    }

    public Note addNote(Journal journal, User creator, NewNoteRequest newNoteRequest) {
        return noteRepository.save(NoteMapper.mapToNote(journal, creator, newNoteRequest));
    }
}
