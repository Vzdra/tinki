package it.finki.tinki.model.pojo;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class AccountLoginDataPojo {
    private String account;
    private String password;
    private AccountType type;
}
