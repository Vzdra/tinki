package it.finki.tinki.model.dto.register.account;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDTO extends AccountRegisterDTO {
    String surname;
    List<Long> retainedSkills;
    List<Long> skillsToLearn;
}
