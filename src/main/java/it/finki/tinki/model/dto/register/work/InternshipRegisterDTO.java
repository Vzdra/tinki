package it.finki.tinki.model.dto.register.work;

import lombok.Data;

import java.util.List;

@Data
public class InternshipRegisterDTO extends WorkRegisterDTO {
    int openSpots;
    List<Long> skillsTrained;
}
