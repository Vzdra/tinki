package it.finki.tinki.web.controller;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.dto.register.account.CompanyRegisterDTO;
import it.finki.tinki.model.dto.register.account.TeamRegisterDTO;
import it.finki.tinki.model.dto.register.account.UserRegisterDTO;
import it.finki.tinki.model.dto.response.account.CompanyResponseDTO;
import it.finki.tinki.model.dto.response.account.TeamResponseDTO;
import it.finki.tinki.model.dto.response.account.UserResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.BuilderService;
import it.finki.tinki.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/edit/account")
public class AccountEditController {

    AccountService accountService;
    SkillService skillService;
    BuilderService builderService;

    public AccountEditController(AccountService accountService, SkillService skillService, BuilderService builderService) {
        this.accountService = accountService;
        this.skillService = skillService;
        this.builderService = builderService;
    }

    @PostMapping(path = "/user/{id}/{email}")
    public UserResponseDTO editUser(@PathVariable(name = "id") Long id,
                                    @PathVariable(name = "email") String email,
                                    @RequestBody UserRegisterDTO body){

        Optional<?> a = this.accountService.findByIdAndEmail(id, email, AccountType.USER);

        if(a.isPresent()){
            List<Skill> retained = this.skillService.returnSkillsBasedOnId(body.getRetainedSkills());
            List<Skill> toLearn = this.skillService.returnSkillsBasedOnId(body.getSkillsToLearn());
            User u = this.accountService.editUser(id, body.getEmail(), body.getName(), body.getSurname(), retained, toLearn);
            return this.builderService.buildUserResponseDTO(u);
        }

        return new UserResponseDTO();
    }

    @PostMapping(path = "/company/{id}/{email}")
    public CompanyResponseDTO editCompany(@PathVariable(name = "id") Long id,
                                          @PathVariable(name = "email") String email,
                                          @RequestBody CompanyRegisterDTO body){

        Optional<?> a = this.accountService.findByIdAndEmail(id, email, AccountType.COMPANY);

        if(a.isPresent()){
            Company c = this.accountService.editCompany(id, body.getEmail(), body.getName(), body.getCountry(), body.getCity(), body.getStreet());
            return this.builderService.buildCompanyResponseDTO(c);
        }

        return new CompanyResponseDTO();
    }

    @PostMapping(path = "/team/{id}/{email}")
    public TeamResponseDTO editTeam(@PathVariable(name = "id") Long id,
                                    @PathVariable(name = "email") String email,
                                    @RequestBody TeamRegisterDTO body){

        Optional<?> a = this.accountService.findByIdAndEmail(id, email, AccountType.TEAM);

        if(a.isPresent()){
            Team t = this.accountService.editTeam(id, body.getEmail(), body.getName(), body.getMembers());
            return this.builderService.buildTeamResponseDTO(t);
        }

        return new TeamResponseDTO();
    }
}
