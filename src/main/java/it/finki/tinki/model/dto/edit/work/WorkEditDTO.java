package it.finki.tinki.model.dto.edit.work;

import lombok.Data;

@Data
public class WorkEditDTO {
    String title;
    String description;
    Long accountId;
    int salary;
}
