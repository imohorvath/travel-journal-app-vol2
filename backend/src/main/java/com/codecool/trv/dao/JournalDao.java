package com.codecool.trv.dao;


import com.codecool.trv.dto.Journal;
import com.codecool.trv.dto.user.User;

import java.util.ArrayList;
import java.util.List;

public class JournalDao {

    private final List<Journal> journals;

    private static int idCounter = 0;

    public JournalDao() {
        this.journals = new ArrayList<>();
    }


    public List<Journal> findAllJournals() {
        return journals;
    }
}
