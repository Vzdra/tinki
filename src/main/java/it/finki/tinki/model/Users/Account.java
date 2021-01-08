package it.finki.tinki.model.Users;

import com.sun.istack.NotNull;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    String name;

    AccountType accountType;

    public Account(){}

    public Account(String email, String password, String name, AccountType accountType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accountType = accountType;
    }
}
