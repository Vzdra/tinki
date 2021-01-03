package it.finki.tinki.controller;

import it.finki.tinki.helper.Matchmaker;
import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Work;
import it.finki.tinki.model.Skill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String testPage(){
        Matchmaker match = new Matchmaker();
        List<Skill> skills = new ArrayList<>();

        skills.add(new Skill("C++"));
        skills.add(new Skill("Java"));
        skills.add(new Skill("Python"));

        List<Skill> user = new ArrayList<>();

        //user.add(new Skill("C++"));
        user.add(new Skill("Java"));
        user.add(new Skill("Python"));

        System.out.println(match.match(skills, user));

        return "Hello";
    }

}
