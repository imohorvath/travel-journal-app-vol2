package com.codecool.trv.dto.note;

import com.codecool.trv.dto.geoip.GeoIP;
import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.user.User;

import java.time.LocalDateTime;

public class Note {

    private final int id;
    private final String text;
    private final LocalDateTime timestamp;
    private final GeoIP geoIP;
    private final Journal journal;
    private final User creator;

    public Note(int id, String text, GeoIP geoIP, Journal journal, User creator) {
        this.id = id;
        this.text = text;
        this.timestamp = LocalDateTime.now();
        this.geoIP = geoIP;
        this.journal = journal;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

    public GeoIP getGeoIP() {
        return geoIP;
    }

    public Journal getJournal() {
        return journal;
    }

    public User getCreator() {
        return creator;
    }
}
