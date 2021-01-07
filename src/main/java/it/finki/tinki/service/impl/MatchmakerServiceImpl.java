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
        List<Match> matches = this.matchRepository.getAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user.getId(), WorkType.INTERNSHIP);
//        List<Match> matches = this.matchRepository.getAllByUserIdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.INTERNSHIP);

        List<Internship> internships = new ArrayList<>();
        matches.forEach(match -> {
            internships.add((Internship) match.getEmbeddedMatchId().getWork());
//            internships.add(this.internshipRepository.findById(match.getWorkId()).get());
        });

        return internships;
    }

    @Override
    public List<Job> getMatchingJobsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user.getId(), WorkType.INTERNSHIP);
//        List<Match> matches = this.matchRepository.getAllByUserIdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.JOB);

        List<Job> jobs = new ArrayList<>();
        matches.forEach(match -> {
            jobs.add((Job) match.getEmbeddedMatchId().getWork());
//            jobs.add(this.jobRepository.findById(match.getWorkId()).get());
        });

        return jobs;
    }

    @Override
    public List<Project> getMatchingProjectsForUser(User user) {
        List<Match> matches = this.matchRepository.getAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(user.getId(), WorkType.INTERNSHIP);
//        List<Match> matches = this.matchRepository.getAllByUserIdAndTypeOrderByCoefficientDesc(user.getId(), WorkType.PROJECT);

        List<Project> projects = new ArrayList<>();
        matches.forEach(match -> {
            projects.add((Project) match.getEmbeddedMatchId().getWork());
//            projects.add(this.projectRepository.findById(match.getWorkId()).get());
        });

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
//            Match m = new Match(job.getId(), user.getId(), coef, WorkType.JOB);
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
//            Match m = new Match(project.getId(), user.getId(), coef, WorkType.JOB);
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
//            Match m = new Match(internship.getId(), user.getId(), coef, WorkType.JOB);
            this.matchRepository.save(m);
        }
    }


}
