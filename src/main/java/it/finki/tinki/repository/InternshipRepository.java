package it.finki.tinki.repository;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllByAccount_Id(Long accountId);
    List<Internship> findAllByTitleContainsOrDescriptionContains(String title, String description);
    List<Internship> findAllBySkillsTrainedContaining(Skill skill);
}
