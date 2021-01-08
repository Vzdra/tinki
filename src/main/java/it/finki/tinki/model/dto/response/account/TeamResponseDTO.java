package it.finki.tinki.model.dto.response.account;

import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamResponseDTO extends LoginResponseDTO {
    private int members;
    private List<JobResponseDTO> jobs;
    private List<ProjectResponseDTO> projects;

    public TeamResponseDTO(){
        this.jobs = new ArrayList<>();
        this.projects = new ArrayList<>();
    }
}
