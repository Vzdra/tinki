package it.finki.tinki.web.controller;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.dto.AccountLoginDTO;
import it.finki.tinki.model.dto.AuthResponseDTO;
import it.finki.tinki.model.dto.LoginResponseDTO;
import it.finki.tinki.model.dto.UserResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.MatchmakerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    AccountService accountService;
    MatchmakerService matchmakerService;

    public LoginController(AccountService accountService, MatchmakerService matchmakerService) {
        this.accountService = accountService;
        this.matchmakerService = matchmakerService;
    }

    @PostMapping(path = "/login")
    public LoginResponseDTO testPage(@RequestBody AccountLoginDTO body) throws ResponseStatusException {

        System.out.println(body);

        Account a1 = accountService.findUser(body.getEmail(), body.getPassword(), body.getType());
        if(a1!=null){
            if(a1.getClass().equals(User.class)){

                UserResponseDTO uDto = new UserResponseDTO();

                uDto.setId(a1.getId());
                uDto.setEmail(a1.getEmail());
                uDto.setName(a1.getName());
                uDto.setType(AccountType.USER);
                uDto.setSurname(((User) a1).getSurname());

                uDto.setRetained(((User) a1).getRetainedSkills());
                uDto.setToLearn(((User) a1).getSkillsToLearn());

                uDto.setInternships(this.matchmakerService.getMatchingInternshipsForUser((User) a1));
                uDto.setJobs(this.matchmakerService.getMatchingJobsForUser((User) a1));
                uDto.setProjects(this.matchmakerService.getMatchingProjectsForUser((User) a1));

                return uDto;
            }else if(a1.getClass().equals(Team.class)){

            }else{

            }
        }

        return null;
    }

}
