package it.finki.tinki.service.impl;

import it.finki.tinki.helper.Matchmaker;
import it.finki.tinki.model.EmbeddedMatchId;
import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Jobs.Work;
import it.finki.tinki.model.Match;
import it.finki.tinki.model.Skill;
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
        List<Match> matches = this.matchRepository.getAllByCombinedId_User_IdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.INTERNSHIP);

        List<Internship> internships = new ArrayList<>();
        matches.forEach(match -> {
            internships.add((Internship) match.getCombinedId().getWork());
        });

        return internships;
    }

    @Override
    public List<Job> getMatchingJobsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByCombinedId_User_IdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.JOB);

        List<Job> jobs = new ArrayList<>();
        matches.forEach(match -> {
            jobs.add((Job) match.getCombinedId().getWork());
        });

        return jobs;
    }

    @Override
    public List<Project> getMatchingProjectsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByCombinedId_User_IdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.PROJECT);

        List<Project> projects = new ArrayList<>();
        matches.forEach(match -> {
            projects.add((Project) match.getCombinedId().getWork());
        });

        return projects;
    }

    @Override
    public void setUpUserJobMatches(Job job, User user) {
        List<Skill> jobSkill = job.getSkillsRequired();
        List<Skill> userSkill = user.getRetainedSkills();

        float coef = Matchmaker.match(jobSkill, userSkill);

        if(coef!=0){
            EmbeddedMatchId matchId = new EmbeddedMatchId(job, user);
            Match m = new Match(matchId, coef, WorkType.JOB);
            this.matchRepository.save(m);
        }
    }

    @Override
    public void setUpUserProjectMatches(Project project, User user) {
        List<Skill> projectSkills = project.getSkillsRequired();
        List<Skill> userSkill = user.getRetainedSkills();

        float coef = Matchmaker.match(projectSkills, userSkill);

        if(coef!=0){
            EmbeddedMatchId matchId = new EmbeddedMatchId(project, user);
            Match m = new Match(matchId, coef, WorkType.PROJECT);
            this.matchRepository.save(m);
        }
    }

    @Override
    public void setUpUserInternshipMatches(Internship internship, User user) {
        List<Skill> internshipSkills = internship.getSkillsTrained();
        List<Skill> userSkill = user.getSkillsToLearn();

        float coef = Matchmaker.match(internshipSkills, userSkill);

        if(coef!=0){
            EmbeddedMatchId matchId = new EmbeddedMatchId(internship, user);
            Match m = new Match(matchId, coef, WorkType.INTERNSHIP);
            this.matchRepository.save(m);
        }
    }


}
