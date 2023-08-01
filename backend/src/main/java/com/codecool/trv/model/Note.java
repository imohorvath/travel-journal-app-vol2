package com.codecool.trv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="notes")
@EntityListeners(AuditingEntityListener.class)
public class Note {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="text", length = Length.LONG32)
    private String text;

    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="created_by_user_id", referencedColumnName = "id")
    //@CreatedBy --- first check how it works
    private User createdBy;

    @OneToOne
    @JoinColumn(name = "geo_ip_id", referencedColumnName = "id")
    private GeoIP geoIP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="journal_id", referencedColumnName = "id")
    private Journal journal;

    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="updated_by_user_id", referencedColumnName = "id")
    //@LastModifiedBy --- first check how it works
    private User updatedBy;

}
