package it.finki.tinki.service.impl;

import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.repository.InternshipRepository;
import it.finki.tinki.repository.JobRepository;
import it.finki.tinki.repository.MatchRepository;
import it.finki.tinki.repository.ProjectRepository;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.SkillService;
import it.finki.tinki.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    JobRepository jobRepository;
    InternshipRepository internshipRepository;
    ProjectRepository projectRepository;
    MatchRepository matchRepository;
    SkillService skillService;
    AccountService accountService;

    public WorkServiceImpl(JobRepository jobRepository,
                           InternshipRepository internshipRepository,
                           ProjectRepository projectRepository,
                           MatchRepository matchRepository,
                           SkillService skillService,
                           AccountService accountService) {
        this.jobRepository = jobRepository;
        this.internshipRepository = internshipRepository;
        this.projectRepository = projectRepository;
        this.matchRepository = matchRepository;
        this.skillService = skillService;
        this.accountService = accountService;
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
        return this.jobRepository.save(j);
    }

    @Override
    public Internship insertInternship(String title, String description, Long adccId, int salary, List<Long> skillsTrained, int openSpots, AccountType type) {
        List<Skill> skills = this.skillService.returnSkillsBasedOnId(skillsTrained);
        Account account = this.accountService.findByIdAndType(adccId, type);
        Internship j = new Internship(title, description, account, salary, skills, openSpots);
        return this.internshipRepository.save(j);
    }

    @Override
    public Project insertProject(String title, String description, Long adccId, int salary, List<Long> skillsRequired, Date validUntil, AccountType type) {
        List<Skill> skills = this.skillService.returnSkillsBasedOnId(skillsRequired);
        Account account = this.accountService.findByIdAndType(adccId, type);
        Project j = new Project(title, description, account, salary, skills, validUntil);
        return this.projectRepository.save(j);
    }
}
