package it.finki.tinki.repository;

import it.finki.tinki.model.EmbeddedMatchId;
import it.finki.tinki.model.Match;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, EmbeddedMatchId> {
    List<Match> findAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(User user, WorkType type);
    List<Match> deleteAllByEmbeddedMatchId_User_Id(Long userId);
}
