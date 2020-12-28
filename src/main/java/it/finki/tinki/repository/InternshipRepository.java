package it.finki.tinki.repository;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllByAccount_Id(Long accountId);
}
