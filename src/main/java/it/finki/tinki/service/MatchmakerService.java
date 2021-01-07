package it.finki.tinki.service;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Users.User;

import java.util.List;

public interface MatchmakerService {
    List<Internship> getMatchingInternshipsForUser(User user);
    List<Job> getMatchingJobsForUser(User user);
    List<Project> getMatchingProjectsForUser(User user);
    void setUpUserJobMatches(Job job, User user);
    void setUpUserProjectMatches(Project project, User user);
    void setUpUserInternshipMatches(Internship internship, User user);
}
