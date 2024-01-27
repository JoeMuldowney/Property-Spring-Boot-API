package com.PropertyManager.Properties.domain.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "location")

public class LocationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private Integer zipCode;

    private String state;

    private Double price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "person_fk", referencedColumnName = "id")
    private PersonEntity person;

}