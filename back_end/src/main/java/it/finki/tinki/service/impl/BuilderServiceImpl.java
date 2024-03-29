package it.finki.tinki.service.impl;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.dto.response.account.CompanyResponseDTO;
import it.finki.tinki.model.dto.response.account.TeamResponseDTO;
import it.finki.tinki.model.dto.response.account.UserResponseDTO;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.BuilderService;
import it.finki.tinki.service.MatchmakerService;
import it.finki.tinki.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuilderServiceImpl implements BuilderService {

    MatchmakerService matchmakerService;
    WorkService workService;

    public BuilderServiceImpl(MatchmakerService matchmakerService, WorkService workService) {
        this.matchmakerService = matchmakerService;
        this.workService = workService;
    }

    @Override
    public UserResponseDTO buildUserResponseDTO(Account a1) {
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
    }

    @Override
    public CompanyResponseDTO buildCompanyResponseDTO(Account a1) {
        CompanyResponseDTO cDto = new CompanyResponseDTO();

        cDto.setError(null);

        cDto.setId(a1.getId());
        cDto.setEmail(a1.getEmail());
        cDto.setName(a1.getName());
        cDto.setType(AccountType.COMPANY);
        cDto.setAddress(((Company) a1).getAddress());

        List<Job> jobs = this.workService.getAllJobsByAccount(a1.getId());
        List<Internship> internships = this.workService.getAllInternshipsByAccount(a1.getId());

        jobs.forEach(job -> {
            JobResponseDTO dto = new JobResponseDTO(job);
            cDto.getJobs().add(dto);
        });

        internships.forEach(internship -> {
            InternshipResponseDTO dto = new InternshipResponseDTO(internship);
            cDto.getInternships().add(dto);
        });

        return cDto;
    }

    @Override
    public TeamResponseDTO buildTeamResponseDTO(Account a1) {
        TeamResponseDTO tDto = new TeamResponseDTO();

        tDto.setError(null);

        tDto.setId(a1.getId());
        tDto.setEmail(a1.getEmail());
        tDto.setName(a1.getName());
        tDto.setType(AccountType.TEAM);
        tDto.setMembers(((Team) a1).getMembers());

        List<Job> jobs = this.workService.getAllJobsByAccount(a1.getId());
        List<Project> projects = this.workService.getAllProjectsByAccount(a1.getId());

        jobs.forEach(job -> {
            JobResponseDTO dto = new JobResponseDTO(job);
            tDto.getJobs().add(dto);
        });

        projects.forEach(project -> {
            ProjectResponseDTO dto = new ProjectResponseDTO(project);
            tDto.getProjects().add(dto);
        });

        return tDto;
    }
}
