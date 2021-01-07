package it.finki.tinki.model.dto;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long id;
    private String email;
    private String name;
    private AccountType type;
}
