package com.codecool.trv.dto.note;

public class NewNote {

    private final String text;
    private final int journalId;
    private final int userId;

    public NewNote(String text, int journalId, int userId) {
        this.text = text;
        this.journalId = journalId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public int getJournalId() {
        return journalId;
    }

    public int getUserId() {
        return userId;
    }
}
