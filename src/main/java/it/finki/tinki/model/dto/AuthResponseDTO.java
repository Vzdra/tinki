package it.finki.tinki.model.dto;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private Long id;
    private String email;
    private String name;
    private AccountType type;

    public AuthResponseDTO() {
        this.id = null;
        this.email = "Wrong username or password!";
        this.name = "Wrong username or password!";
        this.type = null;
    }
}
