package it.finki.tinki.model.Users;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "users")
@Data
public class User extends Account{

    String surname;

    @ManyToMany
    List<Skill> retainedSkills;

    @ManyToMany
    List<Skill> skillsToLearn;

    public User(){}

    public User(String email, String password, String name, AccountType accountType, String surname, List<Skill> retainedSkills, List<Skill> skillsToLearn) {
        super(email, password, name, accountType);
        this.surname = surname;
        this.retainedSkills = retainedSkills;
        this.skillsToLearn = skillsToLearn;
    }

    public String toString(){
        return "";
    }
}
