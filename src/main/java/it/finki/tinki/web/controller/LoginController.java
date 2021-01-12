package it.finki.tinki.web.controller;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.dto.*;
import it.finki.tinki.model.dto.response.account.LoginResponseDTO;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.BuilderService;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    AccountService accountService;
    BuilderService builderService;

    public LoginController(AccountService accountService, BuilderService builderService) {
        this.accountService = accountService;
        this.builderService = builderService;
    }

    @PostMapping(path = "/login")
    public LoginResponseDTO loginProcess(@RequestBody AccountLoginDTO body) throws ResponseStatusException {

        Account a1 = accountService.findUser(body.getEmail(), body.getPassword(), body.getType());

        if(a1!=null){
            if(a1.getClass().equals(User.class)){
                return this.builderService.buildUserResponseDTO(a1);
            }else if(a1.getClass().equals(Team.class)){
                return this.builderService.buildTeamResponseDTO(a1);
            }else{
                return this.builderService.buildCompanyResponseDTO(a1);
            }
        }

        return new LoginResponseDTO();
    }
}
