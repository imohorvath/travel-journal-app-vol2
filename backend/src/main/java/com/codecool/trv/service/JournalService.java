package com.codecool.trv.service;

import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JournalService {


    private final JournalRepository journalRepository;
    private final UserService userService;
    private final NoteService noteService;

    @Autowired
    public JournalService(JournalRepository journalRepository, UserService userService, NoteService noteService) {
        this.journalRepository = journalRepository;
        this.userService = userService;
        this.noteService = noteService;
    }

    public List<Journal> findAllJournalsByUserId(Long userId) {
        return journalRepository.findAllByOwner_IdIs(userId);
    }

    Journal findJournalById(Long id) {
        return journalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
    }

    public Journal findJournalResponse(Long id) {
        return journalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
    }

    public NewJournalResponse addNewJournal(Long userId, NewJournal newJournal) {
        User user = userService.findUserById(userId);

        Journal journal = Journal.builder()
                .title(newJournal.title())
                .owner(user)
                .build();

        if (newJournal.contributorIds().size() != 0) {
            Set<User> contributors = userService.findUsersByIds(newJournal.contributorIds());
            journal.addContributors(contributors);
        }

        Journal savedJournal = journalRepository.save(journal);

        return NewJournalResponse.builder()
                .id(savedJournal.getId())
                .title(savedJournal.getTitle())
                .createdAt(savedJournal.getCreatedAt())
                .contributors(savedJournal.getContributors()
                        .stream()
                        .map(contributor -> new UserResponse(contributor.getId(), contributor.getUsername())).collect(Collectors.toSet()))
                .build();
    }

    public List<Journal> deleteAllJournalsByUserId(int id) {
        //TODO
        return null;
    }

    public Journal deleteJournalById(int id) {
        //TODO
        return null;
    }

    private void deleteAllNotesByJournalId(int id) {
        //TODO
    }

    public List<Note> findAllNotesByJournalId(Long journalId) {
        return noteService.findAllNotesByJournalId(journalId);
    }

    public Note postNoteToJournalById(Long journalId, Long userId, NewNoteRequest newNoteRequest) {
        Journal journal = findJournalById(journalId);
        User creator = userService.findUserById(userId);

        Note note = noteService.addNote(journal, creator, newNoteRequest);
        journal.addNote(note);
        return note;
    }
}
