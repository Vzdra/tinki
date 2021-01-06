package it.finki.tinki.service;

import it.finki.tinki.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> returnSkillsBasedOnId(List<Integer> skillIds);
}
