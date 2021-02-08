package it.finki.tinki.model.dto.register.work;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectRegisterDTO extends WorkRegisterDTO {
    Date validUntil;
    List<Long> skillsRequired;
}
