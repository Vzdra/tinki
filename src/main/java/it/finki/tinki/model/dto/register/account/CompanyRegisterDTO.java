package it.finki.tinki.model.dto.register.account;

import lombok.Data;

@Data
public class CompanyRegisterDTO extends AccountRegisterDTO {
    String country;
    String city;
    String street;
}
