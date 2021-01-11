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
import java.util.ArrayList;
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

        List<Skill> lista;
        lista = this.skillRepository.findAll();

        List<Skill> l2 = new ArrayList<>();
        l2.add(this.skillRepository.findById(3L).get());
        l2.add(this.skillRepository.findById(2L).get());
        l2.add(this.skillRepository.findById(4L).get());
        l2.add(this.skillRepository.findById(7L).get());
        l2.add(this.skillRepository.findById(1L).get());
        l2.add(this.skillRepository.findById(5L).get());
        l2.add(this.skillRepository.findById(9L).get());
        l2.add(this.skillRepository.findById(6L).get());


        List<Skill> l3 = new ArrayList<>();
        l3.add(this.skillRepository.findById(3L).get());
        l3.add(this.skillRepository.findById(2L).get());
        l3.add(this.skillRepository.findById(4L).get());
        l3.add(this.skillRepository.findById(7L).get());

        List<Long> ids = new ArrayList<>();
        lista.forEach(item -> {
            ids.add(item.getId());
        });

        List<Long> smalls = new ArrayList<>();
        l2.forEach(item -> {
            smalls.add(item.getId());
        });

        List<Long> sm2 = new ArrayList<>();
        l3.forEach(item -> {
            sm2.add(item.getId());
        });

        Account c = this.accountService.registerCompany("company@company", "company", "Co.co", "Macedonia", "Skopje", "Pero Nakov");
        Account m = this.accountService.registerTeam("team@team", "team", "TeamRocket", 3);

        this.workService.insertJob("asdfq", "asdfqw", m.getId() ,5000, smalls, AccountType.TEAM);
        this.workService.insertJob("asdfq", "asdfqw", c.getId() ,5000, sm2, AccountType.COMPANY);
        this.workService.insertJob("Asdf", "Asdfa", c.getId() ,5000, ids, AccountType.COMPANY);
        this.workService.insertJob("Asdf", "Asdfa", m.getId() ,5000, ids, AccountType.TEAM);

        this.accountService.registerUser("user@user", "user", "Zoki", "Poki", l3, lista);

    }

}
