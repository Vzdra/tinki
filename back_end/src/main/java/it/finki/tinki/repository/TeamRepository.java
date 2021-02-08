package it.finki.tinki.repository;

import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByEmailAndPassword(String email, String password);
    Optional<Team> findByEmail(String email);
    Optional<Team> findByIdAndEmail(Long id, String email);
}
