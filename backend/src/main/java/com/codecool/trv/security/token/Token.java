package com.codecool.trv.security.token;

import com.codecool.trv.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Token {

        @Id
        @GeneratedValue
        public Long id;

        @Column(unique = true)
        public String token;

        public String tokenType = "BEARER";

        public boolean revoked;

        public boolean expired;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id")
        public User user;
}
