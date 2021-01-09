package it.finki.tinki.service;

import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.model.dto.response.work.WorkResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;

import java.util.Date;
import java.util.List;

public interface WorkService {
    List<Job> getAllJobsByAccount(Long accId);
    List<Internship> getAllInternshipsByAccount(Long accId);
    List<Project> getAllProjectsByAccount(Long accId);
    Job insertJob(String title, String description, Long accId, int salary, List<Long> skillsRequired, AccountType type);
    Internship insertInternship(String title, String description, Long adccId, int salary, List<Long> skillsTrained, int openSpots, AccountType type);
    Project insertProject(String title, String description, Long adccId, int salary, List<Long> skillsRequired, Date validUntil, AccountType type);
    Job editJob(Long id, String title, String description, int salary);
    Internship editInternship(Long id, String title, String description, int salary, int openSpots);
    Project editProject(Long id, String title, String description, int salary);
    List<JobResponseDTO> fullTextJobSearch(String text);
    List<InternshipResponseDTO> fullTextInternshipSearch(String text);
    List<ProjectResponseDTO> fullTextProjectSearch(String text);
    Job getJobById(Long id);
    Internship getInternshipById(Long id);
    Project getProjectById(Long id);
}
