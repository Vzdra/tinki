package it.finki.tinki.repository;

import it.finki.tinki.model.Jobs.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllByAccount_Id(Long accountId);
}
