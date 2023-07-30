package com.codecool.trv.dto.journal;

public class NewJournal {

    private final String title;
    private final int ownerId;

    public NewJournal(String title, int ownerId) {
        this.title = title;
        this.ownerId = ownerId;
    }

    // if we want to make a new journal with contributors already in it,
    // we should add another constructor with Set of users as a parameter

    public String getTitle() {
        return title;
    }

    public int getOwnerId() {
        return ownerId;
    }

}
