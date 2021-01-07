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
    List<Match> getAllByEmbeddedMatchIdUserAndTypeOrderByCoefficientDesc(Long uId, WorkType type);
//    List<Match> getAllByUserIdAndTypeOrderByCoefficientDesc(Long uId, WorkType type);
}
