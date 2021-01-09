package it.finki.tinki.service.impl;

import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Work.Work;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.model.dto.response.work.WorkResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.repository.*;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.MatchmakerService;
import it.finki.tinki.service.SkillService;
import it.finki.tinki.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkServiceImpl implements WorkService {

    JobRepository jobRepository;
    InternshipRepository internshipRepository;
    ProjectRepository projectRepository;
    MatchRepository matchRepository;
    MatchmakerService matchmakerService;
    SkillService skillService;
    AccountService accountService;
    UserRepository userRepository;

    public WorkServiceImpl(JobRepository jobRepository,
                           InternshipRepository internshipRepository,
                           ProjectRepository projectRepository,
                           MatchRepository matchRepository,
                           SkillService skillService,
                           AccountService accountService,
                           UserRepository userRepository,
                           MatchmakerService matchmakerService) {
        this.jobRepository = jobRepository;
        this.internshipRepository = internshipRepository;
        this.projectRepository = projectRepository;
        this.matchRepository = matchRepository;
        this.skillService = skillService;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.matchmakerService = matchmakerService;
    }

    @Override
    public List<Job> getAllJobsByAccount(Long accId) {
        return this.jobRepository.findAllByAccount_Id(accId);
    }

    @Override
    public List<Internship> getAllInternshipsByAccount(Long accId) {
        return this.internshipRepository.findAllByAccount_Id(accId);
    }

    @Override
    public List<Project> getAllProjectsByAccount(Long accId) {
        return this.projectRepository.findAllByAccount_Id(accId);
    }

    @Override
    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    @Override
    public List<Internship> getAllInternships() {
        return this.internshipRepository.findAll();
    }

    @Override
    public List<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    @Override
    public Job insertJob(String title, String description, Long adccId, int salary, List<Long> skillsRequired, AccountType type) {
        List<Skill> skills = this.skillService.returnSkillsBasedOnId(skillsRequired);
        Account account = this.accountService.findByIdAndType(adccId, type);
        Job j = new Job(title, description, account, salary, skills);
        Job jb = this.jobRepository.save(j);

        List<User> users = this.userRepository.findAll();

        users.forEach(user -> {
            this.matchmakerService.setUpUserJobMatches(jb, user);
        });

        return jb;
    }

    @Override
    public Internship insertInternship(String title, String description, Long adccId, int salary, List<Long> skillsTrained, int openSpots, AccountType type) {
        List<Skill> skills = this.skillService.returnSkillsBasedOnId(skillsTrained);
        Account account = this.accountService.findByIdAndType(adccId, type);
        Internship j = new Internship(title, description, account, salary, skills, openSpots);
        Internship jb = this.internshipRepository.save(j);

        List<User> users = this.userRepository.findAll();

        users.forEach(user -> {
            this.matchmakerService.setUpUserInternshipMatches(jb, user);
        });

        return jb;
    }

    @Override
    public Project insertProject(String title, String description, Long adccId, int salary, List<Long> skillsRequired, Date validUntil, AccountType type) {
        List<Skill> skills = this.skillService.returnSkillsBasedOnId(skillsRequired);
        Account account = this.accountService.findByIdAndType(adccId, type);
        Project j = new Project(title, description, account, salary, skills, validUntil);
        Project jb = this.projectRepository.save(j);

        List<User> users = this.userRepository.findAll();

        users.forEach(user -> {
            this.matchmakerService.setUpUserProjectMatches(jb, user);
        });

        return jb;
    }

    @Override
    public List<JobResponseDTO> fullTextJobSearch(String text) {
        List<Skill> skills = this.skillService.returnBasedOnText(text);

        List<Job> jobs = this.jobRepository.findAllByTitleContainsOrDescriptionContains(text,text);
        List<Job> sjob = new ArrayList<>();

        skills.forEach(skill -> {
            sjob.addAll(this.jobRepository.findAllBySkillsRequiredContaining(skill));
        });

        sjob.forEach(job -> {
            if(!jobs.contains(job)){
                jobs.add(job);
            }
        });

        List<JobResponseDTO> jobs2 = new ArrayList<>();

        jobs.forEach(job -> {
            jobs2.add(new JobResponseDTO(job));
        });

        return jobs2;
    }

    @Override
    public List<InternshipResponseDTO> fullTextInternshipSearch(String text) {
        List<Skill> skills = this.skillService.returnBasedOnText(text);

        List<Internship> jobs = this.internshipRepository.findAllByTitleContainsOrDescriptionContains(text,text);
        List<Internship> sjob = new ArrayList<>();

        skills.forEach(skill -> {
            sjob.addAll(this.internshipRepository.findAllBySkillsTrainedContaining(skill));
        });

        sjob.forEach(job -> {
            if(!jobs.contains(job)){
                jobs.add(job);
            }
        });

        List<InternshipResponseDTO> jobs2 = new ArrayList<>();

        jobs.forEach(job -> {
            jobs2.add(new InternshipResponseDTO(job));
        });

        return jobs2;
    }

    @Override
    public List<ProjectResponseDTO> fullTextProjectSearch(String text) {
        List<Skill> skills = this.skillService.returnBasedOnText(text);

        List<Project> jobs = this.projectRepository.findAllByTitleContainsOrDescriptionContains(text,text);
        List<Project> sjob = new ArrayList<>();

        skills.forEach(skill -> {
            sjob.addAll(this.projectRepository.findAllBySkillsRequiredContaining(skill));
        });

        sjob.forEach(job -> {
            if(!jobs.contains(job)){
                jobs.add(job);
            }
        });

        List<ProjectResponseDTO> jobs2 = new ArrayList<>();

        jobs.forEach(job -> {
            jobs2.add(new ProjectResponseDTO(job));
        });

        return jobs2;
    }
}
