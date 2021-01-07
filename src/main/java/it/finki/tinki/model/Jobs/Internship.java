package it.finki.tinki.model.Jobs;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Internship extends Work {

    @ManyToMany(fetch = FetchType.EAGER)
    List<Skill> skillsTrained;

    int openSpots;

    public Internship(){}

    public Internship(String title, String description, Account account, int salary, List<Skill> skillsTrained, int openSpots) {
        super(title, description, account, salary);
        this.skillsTrained = skillsTrained;
        this.openSpots = openSpots;
    }
}
