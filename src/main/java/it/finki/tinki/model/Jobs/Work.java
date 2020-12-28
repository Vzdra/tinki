package it.finki.tinki.model.Jobs;

import it.finki.tinki.model.Users.Account;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String title;
    String description;

    @ManyToOne(targetEntity = Account.class)
    Account account;

    int salary;

    public Work(){}

    public Work(String title, String description, Account account, int salary) {
        this.title = title;
        this.description = description;
        this.account = account;
        this.salary = salary;
    }
}
