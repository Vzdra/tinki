package it.finki.tinki.model.dto;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Jobs.Job;
import lombok.Data;

import java.util.List;

@Data
public class CompanyResponseDTO extends LoginResponseDTO {
    private Address address;
    private List<Job> jobs;
    private List<Internship> internships;
}
