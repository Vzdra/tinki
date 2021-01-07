package it.finki.tinki.model.dto;

import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Skill;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO extends LoginResponseDTO{
    private String surname;
    private List<Skill> retained;
    private List<Skill> toLearn;
    private List<Job> jobs;
    private List<Internship> internships;
    private List<Project> projects;
}
