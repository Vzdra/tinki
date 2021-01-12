package it.finki.tinki.web.controller;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.dto.register.account.CompanyRegisterDTO;
import it.finki.tinki.model.dto.register.account.TeamRegisterDTO;
import it.finki.tinki.model.dto.register.account.UserRegisterDTO;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/register")
public class AccountRegisterController {

    AccountService accountService;
    SkillService skillService;

    public AccountRegisterController(AccountService accountService, SkillService skillService) {
        this.accountService = accountService;
        this.skillService = skillService;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    private Map<String, String> registerUser(@RequestBody UserRegisterDTO body){

        List<Skill> retained = this.skillService.returnSkillsBasedOnId(body.getRetainedSkills());
        List<Skill> toLearn = this.skillService.returnSkillsBasedOnId(body.getSkillsToLearn());
        Account k = this.accountService.registerUser(body.getEmail(), body.getPassword(), body.getName(), body.getSurname(), retained, toLearn);
        Map<String, String> response = new HashMap<>();

        if(k==null){
            response.put("error", "There was an error when trying to register user.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }

    @RequestMapping(path = "/team", method = RequestMethod.POST)
    private Map<String, String> registerTeam(@RequestBody TeamRegisterDTO body){

        Account k = this.accountService.registerTeam(body.getEmail(), body.getPassword(), body.getName(), body.getMembers());
        Map<String, String> response = new HashMap<>();

        if(k==null){
            response.put("error", "There was an error when trying to register team.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }

    @RequestMapping(path = "/company", method = RequestMethod.POST)
    private Map<String, String> registerCompany(@RequestBody CompanyRegisterDTO body){

        Account k = this.accountService.registerCompany(body.getEmail(),
                body.getPassword(), body.getName(), body.getCountry(), body.getCity(), body.getStreet());
        Map<String, String> response = new HashMap<>();

        if(k==null){
            response.put("error", "There was an error when trying to register company.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }
}
