package it.finki.tinki.model.dto.response.work;

import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.Users.Team;
import lombok.Data;

@Data
public class ProjectResponseDTO extends WorkResponseDTO {
    int members;

    public ProjectResponseDTO(Project project){
        super(project.getId(), project.getTitle(), project.getDescription(), project.getSalary(), project.getAccount());
        this.members = ((Team) project.getAccount()).getMembers();
    }
}
