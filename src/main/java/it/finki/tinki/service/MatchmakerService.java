package it.finki.tinki.service;

import it.finki.tinki.model.Match;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Users.User;

import java.util.List;

public interface MatchmakerService {
    List<Internship> getMatchingInternshipsForUser(User user);
    List<Job> getMatchingJobsForUser(User user);
    List<Project> getMatchingProjectsForUser(User user);
    void setUpUserJobMatches(Job job, User user);
    void setUpUserProjectMatches(Project project, User user);
    void setUpUserInternshipMatches(Internship internship, User user);
    List<Match> removeByUserId(Long userId);
}
