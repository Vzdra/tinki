package it.finki.tinki.web.controller;

import it.finki.tinki.helper.Matchmaker;
import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.MatchmakerService;
import it.finki.tinki.service.SkillService;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/register")
public class RegisterController {

    AccountService accountService;
    SkillService skillService;
    WorkService workService;
    MatchmakerService matchmakerService;

    public RegisterController(AccountService accountService, SkillService skillService, WorkService workService, MatchmakerService matchmakerService) {
        this.accountService = accountService;
        this.skillService = skillService;
        this.workService = workService;
        this.matchmakerService = matchmakerService;
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

        if(k==null){
            response.put("error", "There was an error when trying to register user.");
        }else{
            List<Job> jobs = this.workService.getAllJobs();
            List<Project> projects = this.workService.getAllProjects();
            List<Internship> internships = this.workService.getAllInternships();

            jobs.forEach(job -> {
                this.matchmakerService.setUpUserJobMatches(job, (User) k);
            });

            projects.forEach(project -> {
                this.matchmakerService.setUpUserProjectMatches(project, (User) k);
            });

            internships.forEach(internship -> {
                this.matchmakerService.setUpUserInternshipMatches(internship, (User) k);
            });

            response.put("success", "Registration completed successfully.");
        }

        return response;
    }

    @RequestMapping(path = "/team", method = RequestMethod.POST)
    private Map<String, String> registerTeam(@RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam String name,
                                             @RequestParam int members){

        Account k = this.accountService.registerTeam(email, password, name, members);

        Map<String, String> response = new HashMap<>();

        if(k==null){
            response.put("error", "There was an error when trying to register team.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }

    @RequestMapping(path = "/company", method = RequestMethod.POST)
    private Map<String, String> registerTeam(@RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam String name,
                                             @RequestParam String country,
                                             @RequestParam String city,
                                             @RequestParam String street){

        Account k = this.accountService.registerCompany(email, password, name, country, city, street);

        Map<String, String> response = new HashMap<>();

        if(k==null){
            response.put("error", "There was an error when trying to register company.");
        }else{
            response.put("success", "Registration completed successfully.");
        }

        return response;
    }
}
