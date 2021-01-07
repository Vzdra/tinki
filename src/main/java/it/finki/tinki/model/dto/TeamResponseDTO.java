package it.finki.tinki.model.dto;

import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import lombok.Data;

import java.util.List;

@Data
public class TeamResponseDTO extends LoginResponseDTO{
    private int members;
    private List<Job> jobs;
    private List<Project> projects;
}
