package it.finki.tinki.model;

import it.finki.tinki.model.Jobs.Work;
import it.finki.tinki.model.Users.User;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class EmbeddedMatchId implements Serializable {

    @OneToOne
    private Work work;

    @OneToOne
    private User user;

    public EmbeddedMatchId(){ }

    public EmbeddedMatchId(Work work, User user){
        this.work = work;
        this.user = user;
    }
}
