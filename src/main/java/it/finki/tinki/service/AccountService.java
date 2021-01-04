package it.finki.tinki.service;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.enumerator.AccountType;

import java.util.Map;

public interface AccountService {
    Account findUser(String email, String password, AccountType type);
}
