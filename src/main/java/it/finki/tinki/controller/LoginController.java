package it.finki.tinki.controller;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.pojo.AccountLoginDataPojo;
import it.finki.tinki.model.pojo.AuthResponseDataPojo;
import it.finki.tinki.service.AccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/login")
    public AuthResponseDataPojo testPage(@RequestBody AccountLoginDataPojo body) throws ResponseStatusException {

        System.out.println(body);

        Account a1 = accountService.findUser(body.getAccount(), body.getPassword(), body.getType());
        AuthResponseDataPojo resp = new AuthResponseDataPojo();

        if(a1!=null){
            resp.setId(a1.getId());
            resp.setEmail(a1.getEmail());
            resp.setName(a1.getName());
            resp.setType(a1.getAccountType());
        }

        return resp;
    }

}
