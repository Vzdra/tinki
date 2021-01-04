package it.finki.tinki.controller;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.model.pojo.AccountLoginDataPojo;
import it.finki.tinki.repository.CompanyRepository;
import it.finki.tinki.repository.JobRepository;
import it.finki.tinki.repository.TeamRepository;
import it.finki.tinki.repository.UserRepository;
import it.finki.tinki.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    AccountService accountService;

    public TestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/login")
    public Account testPage(@RequestBody AccountLoginDataPojo body){
        return accountService.findUser(body.getAccount(), body.getPassword(), body.getType());
    }

}
