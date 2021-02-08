package it.finki.tinki.model.dto.register.work;

import lombok.Data;

import java.util.List;

@Data
public class JobRegisterDTO extends WorkRegisterDTO{
    List<Long> skillsRequired;
}
