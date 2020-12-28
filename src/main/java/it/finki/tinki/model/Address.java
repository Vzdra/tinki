package it.finki.tinki.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String country;
    String city;
    String street;

    public Address() {}

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }
}
