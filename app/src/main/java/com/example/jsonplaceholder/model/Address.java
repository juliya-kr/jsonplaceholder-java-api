package com.example.jsonplaceholder.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	@Embedded
	private Geo geo;
}
