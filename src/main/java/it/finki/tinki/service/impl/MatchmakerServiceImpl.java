package it.finki.tinki.service.impl;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Match;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.WorkType;
import it.finki.tinki.repository.MatchRepository;
import it.finki.tinki.service.MatchmakerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MatchmakerServiceImpl implements MatchmakerService {

    MatchRepository matchRepository;

    public MatchmakerServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Internship> getMatchingInternshipsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByCombinedId_UserAndTypeOrderByCoefficientDesc(user, WorkType.INTERNSHIP);

        List<Internship> internships = new ArrayList<>();
        matches.forEach(match -> {
            internships.add((Internship) match.getCombinedId().getWork());
        });

        return internships;
    }

    @Override
    public List<Job> getMatchingJobsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByCombinedId_UserAndTypeOrderByCoefficientDesc(user, WorkType.JOB);

        List<Job> jobs = new ArrayList<>();
        matches.forEach(match -> {
            jobs.add((Job) match.getCombinedId().getWork());
        });

        return jobs;
    }

    @Override
    public List<Project> getMatchingProjectsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByCombinedId_UserAndTypeOrderByCoefficientDesc(user, WorkType.PROJECT);

        List<Project> projects = new ArrayList<>();
        matches.forEach(match -> {
            projects.add((Project) match.getCombinedId().getWork());
        });

        return projects;
    }
}
