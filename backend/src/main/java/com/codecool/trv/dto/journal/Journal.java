package com.codecool.trv.dto.journal;
import com.codecool.trv.dto.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Journal {

    private final int id;
    private final String title;
    private final LocalDateTime created;
    private final User owner;
    private final Set<User> contributors;

    public Journal(int id, String title, User owner) {
        this.id = id;
        this.title = title;
        this.created = LocalDateTime.now();
        this.owner = owner;
        this.contributors = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated() {
        return created.toString();
    }

    public User getOwner() {
        return owner;
    }

    public Set<User> getContributors() {
        return contributors;
    }

    public void setContributors(User contributor) {
        contributors.add(contributor);
    }
}
