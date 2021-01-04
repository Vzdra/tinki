package it.finki.tinki.service.impl;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.repository.CompanyRepository;
import it.finki.tinki.repository.TeamRepository;
import it.finki.tinki.repository.UserRepository;
import it.finki.tinki.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    UserRepository userRepository;
    TeamRepository teamRepository;
    CompanyRepository companyRepository;

    public AccountServiceImpl(UserRepository userRepository, TeamRepository teamRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Account findUser(String email, String password, AccountType type) {

        if(type.equals(AccountType.USER)){
            User u1 = userRepository.findByEmailAndPassword(email, password);
            if(u1!=null){
                return u1;
            }
        }
        else if(type.equals(AccountType.TEAM)){
            Team t1 = teamRepository.findByEmailAndPassword(email, password);
            if(t1!=null){
                return t1;
            }
        }
        else if(type.equals(AccountType.COMPANY)){
            Company c1 = companyRepository.findByEmailAndPassword(email, password);
            if(c1!=null){
                return c1;
            }
        }

        return null;
    }
}
