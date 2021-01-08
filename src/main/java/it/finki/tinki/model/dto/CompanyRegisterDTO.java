package it.finki.tinki.model.dto;

import lombok.Data;

@Data
public class CompanyRegisterDTO {
    String email;
    String password;
    String name;
    String country;
    String city;
    String street;
}
