package it.finki.tinki.model.pojo;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class AuthResponseDataPojo {
    Long id;
    String email;
    String name;
    AccountType type;

    public AuthResponseDataPojo() {
        this.id = null;
        this.email = "Wrong username or password!";
        this.name = "Wrong username or password!";
        this.type = null;
    }
}
