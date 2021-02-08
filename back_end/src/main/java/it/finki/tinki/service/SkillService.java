package it.finki.tinki.service;

import it.finki.tinki.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> returnSkillsBasedOnId(List<Long> skillIds);
    List<Skill> returnBasedOnText(String text);
    List<Skill> findAll();
}
