package it.finki.tinki.model;

import it.finki.tinki.model.enumerator.WorkType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Match {

    @Id
    EmbeddedMatchId combinedId;

    float coefficient;

    WorkType type;

    public Match(){}

    public Match(float coefficient, WorkType type) {
        this.coefficient = coefficient;
        this.type = type;
    }
}
