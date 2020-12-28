package it.finki.tinki.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Match {

    @Id
    EmbeddedMatchId combinedId;

    float coefficient;

    public Match(){}

    public Match(float coefficient) {
        this.coefficient = coefficient;
    }
}
