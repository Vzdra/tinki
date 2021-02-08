package it.finki.tinki.model.Users;

import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Team extends Account {

    int members;

    public Team(){}

    public Team(String email, String password, String name, AccountType accountType, int members) {
        super(email, password, name, accountType);
        this.members = members;
    }
}
