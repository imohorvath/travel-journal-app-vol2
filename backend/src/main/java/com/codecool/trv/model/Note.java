package com.codecool.trv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="notes")
public class Note {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="text")
    private String text;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="created_by_user_id", referencedColumnName = "id")
    private User createdBy;

    @OneToOne
    @Column(name="geo_ip_id")
    private GeoIP geoIP;

    @ManyToOne
    @JoinColumn(name="journal_id", referencedColumnName = "id")
    private Journal journal;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name="modified_by_user_id", referencedColumnName = "id")
    private User modifiedBy;

}
