package com.codecool.trv.model;


import com.codecool.trv.security.model.RoleType;
import com.codecool.trv.security.model.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="deactivated")
    private boolean deactivated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private final Set<Journal> journals = new HashSet<>();;

    @ManyToMany(mappedBy = "contributors")
    @JsonIgnore
    private final Set<Journal> contributedJournals = new HashSet<>();

    private RoleType role;

    @OneToMany (mappedBy = "user")
    private final Set<Token> tokens = new HashSet<>();

}
