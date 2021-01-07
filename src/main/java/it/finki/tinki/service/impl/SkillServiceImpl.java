package it.finki.tinki.service.impl;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.exception.SkillNotFoundException;
import it.finki.tinki.repository.SkillRepository;
import it.finki.tinki.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> returnSkillsBasedOnId(List<Long> skillIds) {

        List<Skill> list = new ArrayList<>();

        skillIds.forEach(skill -> {
            this.skillRepository.findById(skill).ifPresent(list::add);
        });

        return list;
    }
}
