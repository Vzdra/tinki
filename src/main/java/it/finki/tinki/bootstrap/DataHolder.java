package it.finki.tinki.bootstrap;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.EmbeddedMatchId;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Work;
import it.finki.tinki.model.Match;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.model.enumerator.WorkType;
import it.finki.tinki.repository.*;
import it.finki.tinki.service.AccountService;
import it.finki.tinki.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(skillRepository.findAll().size()==0){
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

            skillRepository.save(s1);
            skillRepository.save(s2);
            skillRepository.save(s3);
            skillRepository.save(s4);
            skillRepository.save(s5);
            skillRepository.save(s6);
            skillRepository.save(s7);
            skillRepository.save(s8);
            skillRepository.save(s9);
            skillRepository.save(s0);
        }

        List<Skill> lista;
        lista = skillRepository.findAll();

        List<Skill> l2 = new ArrayList<>();
        l2.add(skillRepository.getOne(3L));
        l2.add(skillRepository.getOne(0L));
        l2.add(skillRepository.getOne(1L));
        l2.add(skillRepository.getOne(2L));
        l2.add(skillRepository.getOne(4L));
        l2.add(skillRepository.getOne(5L));
        l2.add(skillRepository.getOne(6L));
        l2.add(skillRepository.getOne(7L));


        List<Skill> l3 = new ArrayList<>();
        l3.add(skillRepository.getOne(3L));
        l3.add(skillRepository.getOne(0L));
        l3.add(skillRepository.getOne(5L));
        l3.add(skillRepository.getOne(6L));
        l3.add(skillRepository.getOne(7L));

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

        Account c = this.accountService.registerCompany("asdf@asdf", "pass", "Co.co", "Macedonia", "Skopje", "Pero Nakov");
        Account m = this.accountService.registerTeam("asdf", "asd", "TeamRocket", 3);

        Job j = this.workService.insertJob("Asdf", "Asdfa", c.getId() ,5000, ids, AccountType.COMPANY);
        Job k = this.workService.insertJob("Asdf", "Asdfa", m.getId() ,5000, ids, AccountType.TEAM);
        Job z = this.workService.insertJob("asdfq", "asdfqw", m.getId() ,5000, smalls, AccountType.TEAM);
        Job n = this.workService.insertJob("asdfq", "asdfqw", c.getId() ,5000, sm2, AccountType.COMPANY);

        Account u = this.accountService.registerUser("asdf", "asdf", "Zoki", "Poki", lista, lista);

    }

}
