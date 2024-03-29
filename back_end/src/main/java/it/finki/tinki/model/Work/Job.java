package it.finki.tinki.model.Work;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Job extends Work {

    @ManyToMany(fetch = FetchType.EAGER)
    List<Skill> skillsRequired;

    public Job(){}

    public Job(String title, String description, Account account, int salary, List<Skill> skillsRequired) {
        super(title, description, account, salary);
        this.skillsRequired = skillsRequired;
    }
}
