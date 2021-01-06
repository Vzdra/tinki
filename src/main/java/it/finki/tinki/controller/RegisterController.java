package it.finki.tinki.controller;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/register")
public class RegisterController {

    AccountService accountService;
    SkillService skillService;

    public RegisterController(AccountService accountService, SkillService skillService) {
        this.accountService = accountService;
        this.skillService = skillService;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    private Map<String, String> registerUser(@RequestParam String email,
                                                @RequestParam String password,
                                                @RequestParam String name,
                                                @RequestParam String surname,
                                                @RequestParam List<Integer> retainedSkills,
                                                @RequestParam List<Integer> skillsToLearn){

        List<Skill> retained = this.skillService.returnSkillsBasedOnId(retainedSkills);
        List<Skill> toLearn = this.skillService.returnSkillsBasedOnId(skillsToLearn);

        Account k = this.accountService.registerUser(email, password, name, surname, retained, toLearn);

        Map<String, String> response = new HashMap<>();

        if(k!=null){
            response.put("error", "There was an error when trying to register user.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }


}
