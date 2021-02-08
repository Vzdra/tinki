package it.finki.tinki.model.dto.response.work;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Users.Company;
import lombok.Data;

import java.util.List;

@Data
public class InternshipResponseDTO extends WorkResponseDTO {
    int salary;
    int openSpots;
    Address accountAddress;
    List<Skill> skillsTrained;

    public InternshipResponseDTO(){
        super("Internship!");
    }

    public InternshipResponseDTO(Internship internship){
        super(internship.getId(), internship.getTitle(), internship.getDescription(), internship.getSalary(), internship.getAccount());
        this.salary = internship.getSalary();
        this.openSpots = internship.getOpenSpots();
        this.accountAddress = ((Company) internship.getAccount()).getAddress();
        this.skillsTrained = internship.getSkillsTrained();
    }
}
