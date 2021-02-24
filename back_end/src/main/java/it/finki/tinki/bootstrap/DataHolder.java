package it.finki.tinki.bootstrap;

import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.repository.*;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.WorkService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataHolder {

    SkillRepository skillRepository;
    AccountService accountService;
    WorkService workService;
    MatchRepository matchRepository;

    public DataHolder(SkillRepository skillRepository,
                      AccountService accountService,
                      WorkService workService,
                      MatchRepository matchRepository) {
        this.skillRepository = skillRepository;
        this.accountService = accountService;
        this.workService = workService;
        this.matchRepository = matchRepository;
    }

    @PostConstruct
    public void init(){
        if(this.skillRepository.findAll().size()==0){
            Skill s1 = new Skill("C++");
            Skill s2 = new Skill("Java");
            Skill s3 = new Skill("Python");
            Skill s4 = new Skill("JavaScript");
            Skill s5 = new Skill("React");
            Skill s6 = new Skill("Spring");
            Skill s7 = new Skill("C#");
            Skill s8 = new Skill(".NET");
            Skill s9 = new Skill("NodeJs");
            Skill s0 = new Skill("Go");

            this.skillRepository.save(s1);
            this.skillRepository.save(s2);
            this.skillRepository.save(s3);
            this.skillRepository.save(s4);
            this.skillRepository.save(s5);
            this.skillRepository.save(s6);
            this.skillRepository.save(s7);
            this.skillRepository.save(s8);
            this.skillRepository.save(s9);
            this.skillRepository.save(s0);
        }

        if(!this.accountService.hasData()){
            List<Skill> lista;
            lista = this.skillRepository.findAll();

            List<Skill> lista2 = new ArrayList<>();
            lista2.add(this.skillRepository.findById(3L).get());
            lista2.add(this.skillRepository.findById(2L).get());
            lista2.add(this.skillRepository.findById(4L).get());
            lista2.add(this.skillRepository.findById(7L).get());
            lista2.add(this.skillRepository.findById(1L).get());
            lista2.add(this.skillRepository.findById(5L).get());


            List<Skill> lista3 = new ArrayList<>();
            lista3.add(this.skillRepository.findById(3L).get());
            lista3.add(this.skillRepository.findById(2L).get());
            lista3.add(this.skillRepository.findById(4L).get());

            List<Long> allSkills = new ArrayList<>();
            lista.forEach(item -> {
                allSkills.add(item.getId());
            });

            List<Long> skills8 = new ArrayList<>();
            lista2.forEach(item -> {
                skills8.add(item.getId());
            });

            List<Long> skills4 = new ArrayList<>();
            lista3.forEach(item -> {
                skills4.add(item.getId());
            });

            Account c = this.accountService.registerCompany("company@company", "company", "Co.Co", "Macedonia", "Skopje", "Pero Nakov");
            Account ct = this.accountService.registerCompany("company@co", "company", "Co.Co", "Macedonia", "Skopje", "Pero Nakov");
            Account m = this.accountService.registerTeam("team@team", "team", "TeamRocket", 3);

            this.workService.insertJob("Team Job 1", "Team job 1 requires 3 members!", m.getId() ,1515, allSkills, AccountType.TEAM);
            this.workService.insertJob("Team Job 2", "Team job 2 requires 2 members!", m.getId() ,5511, skills4, AccountType.TEAM);
            this.workService.insertJob("Company Job 1", "Company job 1 requires 3 members!", c.getId() ,12345, allSkills, AccountType.COMPANY);
            this.workService.insertJob("Company Job 2", "Company job 2 requires 3 members!", ct.getId() ,8901, skills8, AccountType.COMPANY);
            this.workService.insertInternship("Internship 1", "Opportunity to learn new things!", c.getId() ,5000, skills4, 6, AccountType.COMPANY);
            this.workService.insertInternship("Internship 2", "Opportunity to learn new things!", c.getId() ,5000, allSkills, 3, AccountType.COMPANY);
            this.workService.insertProject("Project 1", "Project that lasts few days!", m.getId() ,5000, skills8, new Date(), AccountType.TEAM);
            this.workService.insertProject("Project 2", "Project that ends soon!", m.getId() ,5000, skills4, new Date(), AccountType.TEAM);
            this.workService.insertJob("Company Job 3", "Company job 1 requires 3 members!", c.getId() ,12345, allSkills, AccountType.COMPANY);
            this.workService.insertJob("Company Job 4", "Company job 2 requires 3 members!", ct.getId() ,8901, skills8, AccountType.COMPANY);
            this.workService.insertInternship("Internship 3", "Opportunity to learn new things!", c.getId() ,5000, skills4, 6, AccountType.COMPANY);
            this.workService.insertInternship("Internship 4", "Opportunity to learn new things!", c.getId() ,5000, allSkills, 3, AccountType.COMPANY);
            this.workService.insertProject("Project 3", "Project that lasts few days!", m.getId() ,5000, skills8, new Date(), AccountType.TEAM);
            this.workService.insertProject("Project 4", "Project that ends soon!", m.getId() ,5000, skills4, new Date(), AccountType.TEAM);

            //this.accountService.registerUser("user@user", "user", "Zoki", "Poki", lista3, lista);
        }
    }
}
