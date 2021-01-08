package it.finki.tinki.model.dto;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Skill;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseDTO extends LoginResponseDTO{
    private String surname;
    private List<Skill> retained;
    private List<Skill> toLearn;
    private List<JobResponseDTO> jobs;
    private List<InternshipResponseDTO> internships;
    private List<ProjectResponseDTO> projects;

    public UserResponseDTO(){
        retained = new ArrayList<>();
        toLearn = new ArrayList<>();
        jobs = new ArrayList<>();
        internships = new ArrayList<>();
        projects = new ArrayList<>();
    }
}
