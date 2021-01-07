package it.finki.tinki.model;

import com.sun.istack.NotNull;
import it.finki.tinki.model.Jobs.Work;
import it.finki.tinki.model.Users.User;
import it.finki.tinki.model.enumerator.WorkType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Match {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
//
//    @NotNull
//    Long workId;
//
//    @NotNull
//    Long userId;

    @Id
    EmbeddedMatchId embeddedMatchId;

    float coefficient;

    WorkType type;

    public Match(){}

//    public Match(Long workId, Long userId, float coefficient, WorkType type) {
//        this.workId = workId;
//        this.userId = userId;
//        this.coefficient = coefficient;
//        this.type = type;
//    }

    public Match(EmbeddedMatchId embeddedMatchId, float coefficient, WorkType type) {
        this.embeddedMatchId = embeddedMatchId;
        this.coefficient = coefficient;
        this.type = type;
    }
}
