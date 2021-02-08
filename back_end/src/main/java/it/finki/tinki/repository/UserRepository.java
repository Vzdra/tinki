package it.finki.tinki.repository;

import it.finki.tinki.model.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndEmail(Long id, String email);
}
