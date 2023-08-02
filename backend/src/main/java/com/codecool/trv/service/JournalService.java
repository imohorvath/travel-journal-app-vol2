package com.codecool.trv.service;

import com.codecool.trv.dto.journal.JournalResponse;
import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NewNoteResponse;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.mapper.JournalMapper;
import com.codecool.trv.mapper.NoteMapper;
import com.codecool.trv.mapper.UserMapper;
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

    Journal findJournalById(Long id) throws ResourceNotFoundException {
        return journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Journal not found."));
    }

    public List<JournalResponse> findAllJournalsByUserId(Long userId) {
        List<Journal> journals = journalRepository.findAllByOwner_IdIs(userId);
        return journals
                .stream()
                .map(journal -> JournalMapper.mapToJournalResponse(journal, getContributorsById(journal.getId())))
                .toList();
    }

    public JournalResponse findJournalResponse(Long id) {
        return JournalMapper.mapToJournalResponse(findJournalById(id), getContributorsById(id));
    }

    public NewJournalResponse addNewJournal(Long userId, NewJournal newJournal) {
        User user = userService.findUserById(userId);

        Journal journal = Journal.builder()
                .title(newJournal.title())
                .owner(user)
                .build();

        if (newJournal.contributorIds().size() != 0) {
            Set<Long> contributorIds = newJournal.contributorIds()
                    .stream()
                    .filter(id -> id != userId)
                    .collect(Collectors.toSet());
            Set<User> contributors = userService.findUsersByIds(contributorIds);
            journal.addContributorSet(contributors);
        }

        Journal savedJournal = journalRepository.save(journal);

        return JournalMapper.mapToNewJournalResponse(savedJournal, getContributorsById(savedJournal.getId()));
    }

    public void deleteAllJournalsByUserId(Long id) {
        //TODO
    }

    public void deleteJournalById(Long id) throws ResourceNotFoundException {
        //TODO check if the user who sends the request,
        // is the owner of the journal,
        // otherwise don't execute that.
        Journal journalToDelete = findJournalById(id);
        //noteService.deleteAllNotesByJournalId(id);
        journalRepository.delete(journalToDelete);
    }

    public void deleteAllNotesByJournalId(Long id) {
        noteService.deleteAllNotesByJournalId(id);
    }

    public List<NoteResponse> findAllNotesByJournalId(Long journalId) {
        return noteService.findAllNotesByJournalId(journalId);
    }

    public NewNoteResponse postNoteToJournalById(Long journalId, Long userId, NewNoteRequest newNoteRequest) {
        Journal journal = findJournalById(journalId);
        User creator = userService.findUserById(userId);

        Note savedNote = noteService.addNote(journal, creator, newNoteRequest);
        journal.addNote(savedNote);

        return NoteMapper.mapToNewNoteResponse(savedNote);
    }

    public Set<UserResponse> getContributorsById(Long journalId) {
        return findJournalById(journalId).getContributors()
                .stream()
                .map(UserMapper::mapToUserResponse).collect(Collectors.toSet());
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

        return UserMapper.mapToUserResponse(userToAdd);
    }

    public void deleteContributorFromJournal(Long journalId, Long userId) {
        Journal journal = findJournalById(journalId);
        User userToRemove = userService.findUserById(userId);

        try {
            journal.deleteContributor(userToRemove);
            journalRepository.save(journal);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("User with ID " + userId + " is not a contributor to this journal.");
        }
    }

    public List<JournalResponse> findAllJournalsByContributorId(Long userId) {
        List<Journal> journals = journalRepository.findAllByContributors_Id(userId);
        return journals
                .stream()
                .map(journal -> JournalMapper.mapToJournalResponse(journal, getContributorsById(journal.getId())))
                .toList();
    }

}
