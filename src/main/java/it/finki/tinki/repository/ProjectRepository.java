package it.finki.tinki.repository;

import it.finki.tinki.model.Work.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByAccount_Id(Long accountId);
}
