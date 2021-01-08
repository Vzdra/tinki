package it.finki.tinki.service.impl;

import it.finki.tinki.helper.Matchmaker;
import it.finki.tinki.model.EmbeddedMatchId;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Match;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.WorkType;
import it.finki.tinki.repository.InternshipRepository;
import it.finki.tinki.repository.JobRepository;
import it.finki.tinki.repository.MatchRepository;
import it.finki.tinki.repository.ProjectRepository;
import it.finki.tinki.service.MatchmakerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MatchmakerServiceImpl implements MatchmakerService {

    MatchRepository matchRepository;
    JobRepository jobRepository;
    InternshipRepository internshipRepository;
    ProjectRepository projectRepository;

    public MatchmakerServiceImpl(MatchRepository matchRepository,
                                 JobRepository jobRepository,
                                 InternshipRepository internshipRepository,
                                 ProjectRepository projectRepository) {
        this.matchRepository = matchRepository;
        this.jobRepository = jobRepository;
        this.internshipRepository = internshipRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Internship> getMatchingInternshipsForUser(User user) {
        List<Match> matches = this.matchRepository.findAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user, WorkType.INTERNSHIP);

        List<Internship> internships = new ArrayList<>();
        if(matches.size()!=0){
            matches.forEach(match -> {
                internships.add((Internship) match.getEmbeddedMatchId().getWork());
            });
        }

        return internships;
    }

    @Override
    public List<Job> getMatchingJobsForUser(User user) {
        List<Match> matches = this.matchRepository.findAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user, WorkType.JOB);

        List<Job> jobs = new ArrayList<>();
        if(matches.size()!=0) {
            matches.forEach(match -> {
                jobs.add((Job) match.getEmbeddedMatchId().getWork());
            });
        }

        return jobs;
    }

    @Override
    public List<Project> getMatchingProjectsForUser(User user) {
        List<Match> matches = this.matchRepository.findAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user, WorkType.PROJECT);

        List<Project> projects = new ArrayList<>();
        if(matches.size()!=0) {
            matches.forEach(match -> {
                projects.add((Project) match.getEmbeddedMatchId().getWork());
            });
        }

        return projects;
    }

    @Override
    public void setUpUserJobMatches(Job job, User user) {
        List<Skill> jobSkill = job.getSkillsRequired();
        List<Skill> userSkill = user.getRetainedSkills();

        float coef = Matchmaker.match(jobSkill, userSkill);

        if(coef!=0){
            EmbeddedMatchId embeddedMatchId = new EmbeddedMatchId(job, user);
            Match m = new Match(embeddedMatchId, coef, WorkType.JOB);
            this.matchRepository.save(m);
        }
    }

    @Override
    public void setUpUserProjectMatches(Project project, User user) {
        List<Skill> projectSkills = project.getSkillsRequired();
        List<Skill> userSkill = user.getRetainedSkills();

        float coef = Matchmaker.match(projectSkills, userSkill);

        if(coef!=0){
            EmbeddedMatchId embeddedMatchId = new EmbeddedMatchId(project, user);
            Match m = new Match(embeddedMatchId, coef, WorkType.PROJECT);
            this.matchRepository.save(m);
        }
    }

    @Override
    public void setUpUserInternshipMatches(Internship internship, User user) {
        List<Skill> internshipSkills = internship.getSkillsTrained();
        List<Skill> userSkill = user.getSkillsToLearn();

        float coef = Matchmaker.match(internshipSkills, userSkill);

        if(coef!=0){
            EmbeddedMatchId embeddedMatchId = new EmbeddedMatchId(internship, user);
            Match m = new Match(embeddedMatchId, coef, WorkType.PROJECT);
            this.matchRepository.save(m);
        }
    }


}
