package it.finki.tinki.web.controller;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.dto.*;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.MatchmakerService;
import it.finki.tinki.service.WorkService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    AccountService accountService;
    MatchmakerService matchmakerService;
    WorkService workService;

    public LoginController(AccountService accountService, MatchmakerService matchmakerService, WorkService workService) {
        this.accountService = accountService;
        this.matchmakerService = matchmakerService;
        this.workService = workService;
    }

    @PostMapping(path = "/login")
    public LoginResponseDTO testPage(@RequestBody AccountLoginDTO body) throws ResponseStatusException {

        System.out.println(body);

        Account a1 = accountService.findUser(body.getEmail(), body.getPassword(), body.getType());

        if(a1!=null){
            if(a1.getClass().equals(User.class)){

                UserResponseDTO uDto = new UserResponseDTO();

                uDto.setError(null);

                uDto.setId(a1.getId());
                uDto.setEmail(a1.getEmail());
                uDto.setName(a1.getName());
                uDto.setType(AccountType.USER);
                uDto.setSurname(((User) a1).getSurname());

                uDto.setRetained(((User) a1).getRetainedSkills());
                uDto.setToLearn(((User) a1).getSkillsToLearn());

                List<Job> matchedJobs = this.matchmakerService.getMatchingJobsForUser((User) a1);
                List<Project> matchedProjects = this.matchmakerService.getMatchingProjectsForUser((User) a1);
                List<Internship> matchedInternships = this.matchmakerService.getMatchingInternshipsForUser((User) a1);

                matchedJobs.forEach(job -> {
                    JobResponseDTO dto = new JobResponseDTO(job);
                    uDto.getJobs().add(dto);
                });

                matchedProjects.forEach(project -> {
                    ProjectResponseDTO dto = new ProjectResponseDTO(project);
                    uDto.getProjects().add(dto);
                });

                matchedInternships.forEach(internship -> {
                    InternshipResponseDTO dto = new InternshipResponseDTO(internship);
                    uDto.getInternships().add(dto);
                });


                return uDto;

            }else if(a1.getClass().equals(Team.class)){

                TeamResponseDTO tDto = new TeamResponseDTO();

                tDto.setError(null);

                tDto.setId(a1.getId());
                tDto.setEmail(a1.getEmail());
                tDto.setName(a1.getName());
                tDto.setType(AccountType.USER);
                tDto.setMembers(((Team) a1).getMembers());

                tDto.setJobs(this.workService.getAllJobsByAccount(a1.getId()));
                tDto.setProjects(this.workService.getAllProjectsByAccount(a1.getId()));

                return tDto;

            }else{

                CompanyResponseDTO cDto = new CompanyResponseDTO();

                cDto.setError(null);

                cDto.setId(a1.getId());
                cDto.setEmail(a1.getEmail());
                cDto.setName(a1.getName());
                cDto.setType(AccountType.USER);
                cDto.setAddress(((Company) a1).getAddress());

                cDto.setJobs(this.workService.getAllJobsByAccount(a1.getId()));
                cDto.setInternships(this.workService.getAllInternshipsByAccount(a1.getId()));

                return cDto;

            }
        }

        return new LoginResponseDTO();
    }

}
