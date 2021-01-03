package it.finki.tinki.helper;

import it.finki.tinki.model.Skill;
import java.util.List;

public class Matchmaker {

    public float match(List<Skill> work, List<Skill> user){

        float coef = work.size();
        float k = 0;

        for (Skill skill : work) {
            for (Skill value : user) {
                if (value.equals(skill)) {
                    k++;
                }
            }
        }

        return k/coef;
    }

}
