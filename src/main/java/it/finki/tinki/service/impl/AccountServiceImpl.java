package it.finki.tinki.service.impl;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.model.exception.InvalidArgumentsException;
import it.finki.tinki.model.exception.UserExistsException;
import it.finki.tinki.repository.AddressRepository;
import it.finki.tinki.repository.CompanyRepository;
import it.finki.tinki.repository.TeamRepository;
import it.finki.tinki.repository.UserRepository;
import it.finki.tinki.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    AddressRepository addressRepository;
    UserRepository userRepository;
    TeamRepository teamRepository;
    CompanyRepository companyRepository;

    public AccountServiceImpl(AddressRepository addressRepository, UserRepository userRepository, TeamRepository teamRepository, CompanyRepository companyRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Account findUser(String email, String password, AccountType type) {

        if(type.equals(AccountType.USER)){
            User u1 = this.userRepository.findByEmailAndPassword(email, password);
            return u1;
        }
        else if(type.equals(AccountType.TEAM)){
            Team t1 = this.teamRepository.findByEmailAndPassword(email, password);
            return t1;
        }
        else if(type.equals(AccountType.COMPANY)){
            Company c1 = this.companyRepository.findByEmailAndPassword(email, password);
            return c1;
        }

        return null;
    }

    public Account registerUser(String email, String password, String name, String surname, List<Skill> retainedSkills, List<Skill> skillsToLearn){
        if(email==null || email.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty() || surname==null || surname.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if(this.userRepository.findByEmail(email).isPresent()){
            throw new UserExistsException();
        }

        User u = new User(email, password, name, AccountType.USER, surname, retainedSkills, skillsToLearn);
        return this.userRepository.save(u);
    }

    public Account registerTeam(String email, String password, String name, int members){
        if(email==null || email.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if(this.teamRepository.findByEmail(email).isPresent()){
            throw new UserExistsException();
        }

        Team t = new Team(email, password, name, AccountType.TEAM, members);
        return this.teamRepository.save(t);
    }

    public Account registerCompany(String email, String password, String name, String country, String city, String street){
        if(email==null || email.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty()
                || country==null || country.isEmpty() || city==null || city.isEmpty() || street==null || street.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if(this.companyRepository.findByEmail(email).isPresent()){
            throw new UserExistsException();
        }

        Address a = new Address(country, city, street);
        this.addressRepository.save(a);
        Company c = new Company(email, password, name, AccountType.COMPANY, a);
        return this.companyRepository.save(c);
    }

    public Account findByIdAndType(Long id, AccountType type){
        switch (type){
            case COMPANY:
                return this.companyRepository.findById(id).get();
            case TEAM:
                return this.teamRepository.findById(id).get();
            case USER:
                return this.userRepository.findById(id).get();
        }

        return null;
    }
}
