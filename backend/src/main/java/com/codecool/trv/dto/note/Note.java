package com.codecool.trv.dto.note;

import com.codecool.trv.dto.geoip.GeoIP;
import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.user.User;

import java.time.LocalDateTime;

public class Note {

    private final int id;
    private final String note;
    private final LocalDateTime timestamp;
    private final GeoIP geoIP;
    private final Journal journal;
    private final User user;

    public Note(int id, String note, LocalDateTime timestamp, GeoIP geoIP, Journal journal, User user) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
        this.geoIP = geoIP;
        this.journal = journal;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public GeoIP getGeoIP() {
        return geoIP;
    }

    public Journal getJournal() {
        return journal;
    }

    public User getUser() {
        return user;
    }
}
