package it.finki.tinki.model.Users;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Company extends Account{

    @OneToOne
    Address address;

    public Company(){}

    public Company(String email, String password, String name, AccountType accountType, Address address) {
        super(email, password, name, accountType);
        this.address = address;
    }
}
