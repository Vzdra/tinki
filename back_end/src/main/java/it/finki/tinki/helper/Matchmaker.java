package it.finki.tinki.helper;

import it.finki.tinki.model.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Matchmaker {

    public static float match(List<Skill> work, List<Skill> user){

        float coef = work.size();
        float k = 0;

        for (Skill skill : work) {
            for (Skill value : user) {
                if (value.equals(skill)) {
                    k++;
                }
            }
        }

        System.out.println(k/coef);

        return k/coef;
    }

}
