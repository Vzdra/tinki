package it.finki.tinki.web.controller;

import it.finki.tinki.model.Skill;
import it.finki.tinki.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/skills")
public class SkillController {

    SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping()
    public List<Skill> returnSkills(){
        return this.skillService.findAll();
    }
}
