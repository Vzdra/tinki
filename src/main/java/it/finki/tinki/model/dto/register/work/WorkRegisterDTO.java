package it.finki.tinki.model.dto.register.work;

import lombok.Data;

@Data
public class WorkRegisterDTO {
    String title;
    String description;
    Long accountId;
    int salary;
}
