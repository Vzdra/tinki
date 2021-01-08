package it.finki.tinki.repository;

import it.finki.tinki.model.Work.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
    List<Job> findAllByAccount_Id(Long accountId);
}
