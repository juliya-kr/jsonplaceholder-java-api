package com.example.jsonplaceholder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private Long id;
    private String name;
    private String username;
    private String email;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "address_street")),
        @AttributeOverride(name = "suite", column = @Column(name = "address_suite")),
        @AttributeOverride(name = "city", column = @Column(name = "address_city")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "address_zipcode")),
        @AttributeOverride(name = "geo.lat", column = @Column(name = "address_geo_lat")),
        @AttributeOverride(name = "geo.lng", column = @Column(name = "address_geo_lng"))
    })
    private Address address;
    private String phone;
    private String website;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "company_name")),
        @AttributeOverride(name = "catchPhrase", column = @Column(name = "company_catch_phrase")),
        @AttributeOverride(name = "bs", column = @Column(name = "company_bs"))
    })
    private Company company;
}

