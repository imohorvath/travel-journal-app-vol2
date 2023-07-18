package com.codecool.trv.dao;


import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.dto.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class JournalDao {

    private final List<Journal> journals;

    private static int idCounter = 0;

    public JournalDao() {
        this.journals = new ArrayList<>();
    }

    public List<Journal> findAllJournalsByUserId(int userId) {
        // todo search between contributors as well
       return journals.stream().filter(journal -> journal.getOwner().getId() == userId).toList();
    }

    public Journal findJournalById(int id) {
        return journals.stream()
                .filter(journal -> journal.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No journal found."));
    }

    public int addNewJournal(NewJournal newJournal, User user) {
        idCounter++;
        Journal journal = new Journal(
                idCounter,
                newJournal.getTitle(),
                user
        );
        journals.add(journal);
        return idCounter;
    }


    public Journal deleteJournalById(int id) {
        Journal deleted = findJournalById(id);
        journals.remove(deleted);
        return deleted;
    }

    public List<Journal> deleteAllJournalsByUserId(int id) {
        return null;
    }
}
