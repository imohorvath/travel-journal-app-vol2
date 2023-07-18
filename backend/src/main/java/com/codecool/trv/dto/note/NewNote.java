package com.codecool.trv.dto.note;

public class NewNote {

    private final String note;
    private final int journalId;
    private final int userId;

    public NewNote(String note, int journalId, int userId) {
        this.note = note;
        this.journalId = journalId;
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public int getJournalId() {
        return journalId;
    }

    public int getUserId() {
        return userId;
    }
}
