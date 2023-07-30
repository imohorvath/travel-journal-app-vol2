package com.codecool.trv.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="geo_ips")
public class GeoIP {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="ip_address")
    private String ipAddress;

    @Column(name="device")
    private String device;

    @Column(name="city")
    private String city;

    @Column(name="full_location")
    private String fullLocation;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

}
