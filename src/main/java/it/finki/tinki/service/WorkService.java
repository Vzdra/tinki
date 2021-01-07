package it.finki.tinki.service;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.enumerator.AccountType;

import java.util.Date;
import java.util.List;

public interface WorkService {
    List<Job> getAllJobsByAccount(Long accId);
    List<Internship> getAllInternshipsByAccount(Long accId);
    List<Project> getAllProjectsByAccount(Long accId);
    List<Job> getAllJobs();
    List<Internship> getAllInternships();
    List<Project> getAllProjects();
    Job insertJob(String title, String description, Long accId, int salary, List<Long> skillsRequired, AccountType type);
    Internship insertInternship(String title, String description, Long adccId, int salary, List<Long> skillsTrained, int openSpots, AccountType type);
    Project insertProject(String title, String description, Long adccId, int salary, List<Long> skillsRequired, Date validUntil, AccountType type);
}
