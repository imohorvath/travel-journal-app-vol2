package com.codecool.trv.dto.journal;

import com.codecool.trv.model.User;

import java.util.Set;

public record NewJournal(String title, Set<Long> contributorIds) {

    // if we want to make a new journal with contributors already in it,
    // we should add another constructor with Set of users as a parameter
}
