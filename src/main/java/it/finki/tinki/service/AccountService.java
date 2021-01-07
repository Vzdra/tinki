package it.finki.tinki.service;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.enumerator.AccountType;

import java.util.List;

public interface AccountService {
    Account findUser(String email, String password, AccountType type);
    Account registerUser(String email, String password, String name, String surname, List<Skill> retainedSkills, List<Skill> skillsToLearn);
    Account registerTeam(String email, String password, String name, int members);
    Account registerCompany(String email, String password, String name, String country, String city, String street);
    Account findByIdAndType(Long accId, AccountType type);
}
