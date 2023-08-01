package com.codecool.trv.model;
import com.codecool.trv.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@Table(name="journals")
@EntityListeners(AuditingEntityListener.class)
public class Journal {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="owner_user_id", referencedColumnName = "id")
    //@CreatedBy --- first check how it works
    private User owner;

    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(cascade=CascadeType.PERSIST)
    private final Set<User> contributors = new HashSet<>();

    @OneToMany(cascade=CascadeType.PERSIST)
    private final Set<Note> notes = new HashSet<>();

    public void addContributors(Set<User> contributorsToAdd){
        contributors.addAll(contributorsToAdd);
    };

    public void addNote(Note note) {
        notes.add(note);
    }

}
