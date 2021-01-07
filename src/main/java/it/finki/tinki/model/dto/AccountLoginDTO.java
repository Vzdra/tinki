package it.finki.tinki.model.dto;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class AccountLoginDTO {
    private String email;
    private String password;
    private AccountType type;
}
