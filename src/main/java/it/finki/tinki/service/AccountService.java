package it.finki.tinki.service;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account findUser(String email, String password, AccountType type);
    Account registerUser(String email, String password, String name, String surname, List<Skill> retainedSkills, List<Skill> skillsToLearn);
    Account registerTeam(String email, String password, String name, int members);
    Account registerCompany(String email, String password, String name, String country, String city, String street);
    Account findByIdAndType(Long accId, AccountType type);
    User editUser(Long id, String email, String name, String surname, List<Skill> retainedSkills, List<Skill> skillsToLearn);
    Company editCompany(Long id, String email, String name, String country, String city, String street);
    Team editTeam(Long id, String email, String name, int members);
    Optional<?> findByIdAndEmail(Long id, String email, AccountType type);
}
