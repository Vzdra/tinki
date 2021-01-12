package it.finki.tinki.model.dto.response.work;

import it.finki.tinki.model.Skill;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Users.Team;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectResponseDTO extends WorkResponseDTO {
    int members;
    Date validUntil;
    List<Skill> skillsRequired;

    public ProjectResponseDTO(){
        super("Project!");
    }

    public ProjectResponseDTO(Project project){
        super(project.getId(), project.getTitle(), project.getDescription(), project.getSalary(), project.getAccount());
        this.members = ((Team) project.getAccount()).getMembers();
        this.validUntil = project.getValidUntil();
        this.skillsRequired = project.getSkillsRequired();
    }
}
