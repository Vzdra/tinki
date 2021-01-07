package it.finki.tinki.model.Jobs;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Project extends Work {

    @ManyToMany(fetch = FetchType.EAGER)
    List<Skill> skillsRequired;

    Date validUntil;

    public Project(){}

    public Project(String title, String description, Account account, int salary, List<Skill> skillsRequired, Date validUntil) {
        super(title, description, account, salary);
        this.skillsRequired = skillsRequired;
        this.validUntil = validUntil;
    }
}
