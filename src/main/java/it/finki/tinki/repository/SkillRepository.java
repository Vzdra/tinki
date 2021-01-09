package it.finki.tinki.repository;

import it.finki.tinki.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findById(Long id);
    List<Skill> findAllByNameContaining(String text);
}
