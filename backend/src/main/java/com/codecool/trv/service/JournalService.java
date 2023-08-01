package com.codecool.trv.service;

import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NewNoteResponse;
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

    public Journal findJournalResponse(Long id) {
        return journalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
    }
      
    public Journal findJournalById(Long id) {
        return journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public NewJournalResponse addNewJournal(Long userId, NewJournal newJournal) {
        User user = userService.findUserById(userId);

        Journal journal = Journal.builder()
                .title(newJournal.title())
                .owner(user)
                .build();
        // TODO the owner could not add to own journal itself as contributor
        if (newJournal.contributorIds().size() != 0) {
            Set<User> contributors = userService.findUsersByIds(newJournal.contributorIds());
            journal.addContributorSet(contributors);
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

    public List<Journal> deleteAllJournalsByUserId(Long id) {
        //TODO
        return null;
    }

    public Journal deleteJournalById(Long id) {
        //TODO
        //??what happens when there are contributors....
        Journal journalToDelete = findJournalById(id);
        noteService.deleteAllNotesByJournalId(id);
        journalRepository.delete(journalToDelete);
        return journalToDelete;
    }

    private void deleteAllNotesByJournalId(Long id) {
        //TODO
    }

    public List<Note> findAllNotesByJournalId(Long journalId) {
        return noteService.findAllNotesByJournalId(journalId);
    }

    public NewNoteResponse postNoteToJournalById(Long journalId, Long userId, NewNoteRequest newNoteRequest) {
        Journal journal = findJournalById(journalId);
        User creator = userService.findUserById(userId);

        Note note = noteService.addNote(journal, creator, newNoteRequest);
        journal.addNote(note);

        return NewNoteResponse.builder()
                .id(note.getId())
                .text(note.getText())
                .createdAt(note.getCreatedAt())
                .createdByUser(note.getCreatedBy().getUsername())
                .journalTitle(note.getJournal().getTitle())
                .updatedByUser(note.getUpdatedBy().getUsername())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    public Set<UserResponse> getContributorsById(Long journalId) {
        return findJournalById(journalId).getContributors()
                .stream()
                .map(contributor -> new UserResponse(contributor.getId(), contributor.getUsername())).collect(Collectors.toSet());
    }

    public UserResponse addContributorToJournal(Long journalId, Long userId) {
        Journal journal = findJournalById(journalId);
        User userToAdd = userService.findUserById(userId);

        try {
            journal.addContributor(userToAdd);
            journalRepository.save(journal);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("User with ID " + userId + " is already a contributor to this journal.");
        }

        return new UserResponse(userToAdd.getId(), userToAdd.getUsername());

    }

    public UserResponse deleteContributorFromJournal(Long journalId, Long userId) {
        Journal journal = findJournalById(journalId);
        User userToRemove = userService.findUserById(userId);

        try {
            journal.deleteContributor(userToRemove);
            journalRepository.save(journal);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("User with ID " + userId + " is not a contributor to this journal.");
        }

        return new UserResponse(userToRemove.getId(), userToRemove.getUsername());
    }

    public List<Journal> findAllJournalsByContributorId(Long userId) {
        //TODO return JournalResponse
        return journalRepository.findAllByContributors_Id(userId);
    }
}
