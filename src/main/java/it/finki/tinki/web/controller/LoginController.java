package it.finki.tinki.web.controller;

import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.dto.*;
import it.finki.tinki.model.dto.response.account.LoginResponseDTO;
import it.finki.tinki.model.dto.response.account.CompanyResponseDTO;
import it.finki.tinki.model.dto.response.account.TeamResponseDTO;
import it.finki.tinki.model.dto.response.account.UserResponseDTO;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.BuilderService;
import it.finki.tinki.service.MatchmakerService;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    AccountService accountService;
    WorkService workService;
    BuilderService builderService;

    public LoginController(AccountService accountService, WorkService workService, BuilderService builderService) {
        this.accountService = accountService;
        this.workService = workService;
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

    @GetMapping(path = "/job/search")
    public List<JobResponseDTO> jobRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextJobSearch(text);
    }

    @GetMapping(path = "/internship/search")
    public List<InternshipResponseDTO> internshipRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextInternshipSearch(text);
    }

    @GetMapping(path = "/project/search")
    public List<ProjectResponseDTO> projectRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextProjectSearch(text);
    }
}
