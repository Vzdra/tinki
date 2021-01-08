package it.finki.tinki.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDTO {
    String email;
    String password;
    String name;
    String surname;
    List<Long> retainedSkills;
    List<Long> skillsToLearn;
}
