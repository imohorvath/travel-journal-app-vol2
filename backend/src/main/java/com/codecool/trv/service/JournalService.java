package com.codecool.trv.service;

import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
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

    public Journal findJournalById(Long id) {
        return journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public NewJournalResponse addNewJournal(Long userId, NewJournal newJournal) {
        User user = userService.findUserById(userId);

        Journal journal = Journal.builder()
                .title(newJournal.title())
                .owner(user)
                .build();

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

    public List<Journal> deleteAllJournalsByUserId(int id) {
        //TODO
        return null;
        /*List<Journal> journals = findAllJournalsByUserId(id);
        for (Journal journal : journals) {
            deleteAllNotesByJournalId(journal.getId());
        }
        return journalDao.deleteAllJournalsByUserId(id);*/
    }

    public Journal deleteJournalById(int id) {
        //TODO
        return null;
        /*deleteAllNotesByJournalId(id);
        return journalDao.deleteJournalById(id);*/
    }

    private void deleteAllNotesByJournalId(int id) {
        //TODO
        /*Journal journal = journalDao.findJournalById(id);
        noteDao.deleteAllNotesByJournalId(journal.getId());*/
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
}
