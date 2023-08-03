package com.codecool.trv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journals")
@EntityListeners(AuditingEntityListener.class)
public class Journal {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id")
    //@CreatedBy --- first check how it works
    private User owner;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinTable(name = "journals_contributors",
            joinColumns = {@JoinColumn(name = "journal_id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "contributor_id", unique = false)})
    private final Set<User> contributors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "journal", cascade = CascadeType.REMOVE)
    private final Set<Note> notes = new HashSet<>();

    public void addContributorSet(Set<User> contributorsToAdd) {
        contributors.addAll(contributorsToAdd);
    }

    public void addContributor(User contributor) {
        if (contributors.contains(contributor)) {
            throw new IllegalArgumentException("User with ID " + contributor.getId() + " is already a contributor to this journal.");
        }
        contributors.add(contributor);
    }

    public void deleteContributor(User contributor) {
        if (!contributors.contains(contributor)) {
            throw new IllegalArgumentException("User with ID " + contributor.getId() + " is not a contributor to this journal.");
        }
        contributors.remove(contributor);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void deleteContributorById(Long userId) {
        Optional<User> contributorToRemove = contributors.stream().filter(user -> user.getId().equals(userId)).findFirst();
        contributorToRemove.ifPresent(this::deleteContributor);
    }
}
