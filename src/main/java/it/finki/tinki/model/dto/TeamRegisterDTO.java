package it.finki.tinki.model.dto;

import lombok.Data;

@Data
public class TeamRegisterDTO {
    String email;
    String password;
    String name;
    int members;
}
