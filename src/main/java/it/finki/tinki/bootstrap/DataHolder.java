package it.finki.tinki.bootstrap;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.repository.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    SkillRepository skillRepository;
    UserRepository userRepository;
    CompanyRepository companyRepository;
    TeamRepository teamRepository;
    AddressRepository addressRepository;

    public DataHolder(SkillRepository skillRepository, UserRepository userRepository, CompanyRepository companyRepository, TeamRepository teamRepository, AddressRepository addressRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.teamRepository = teamRepository;
        this.addressRepository = addressRepository;
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

        userRepository.save(new User("asdf", "asdf", "Zoki", AccountType.USER, "Poki", lista, lista));

        addressRepository.save(new Address("asdf", "asdf", "asdf"));
        companyRepository.save(new Company("asdf@asdf", "pass", "Co.co", AccountType.COMPANY, addressRepository.findAll().get(0)));
    }

}
