package com.codecool.trv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
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

    @ManyToOne
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id")
    //@CreatedBy --- first check how it works
    private User owner;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Set<User> contributors = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Set<Note> notes = new HashSet<>();

    public void addContributorSet(Set<User> contributorsToAdd) {
        for (User contributorToAdd : contributorsToAdd) {
            boolean contributorExists = contributors.stream()
                    .anyMatch(existingContributor -> existingContributor.getId().equals(contributorToAdd.getId()));

            if (!contributorExists) {
                contributors.add(contributorToAdd);
            }
        }
    }

    public void addContributor(User contributor) {
        if (contributors.contains(contributor)) {
            throw new IllegalArgumentException("User with ID " + contributor.getId() + " is already a contributor to this journal.");
        }
        contributors.add(contributor);
    }

}
