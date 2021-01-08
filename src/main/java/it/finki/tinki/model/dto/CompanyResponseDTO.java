package it.finki.tinki.model.dto;

import it.finki.tinki.model.Address;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyResponseDTO extends LoginResponseDTO {
    private Address address;
    private List<JobResponseDTO> jobs;
    private List<InternshipResponseDTO> internships;

    public CompanyResponseDTO(){
        this.jobs = new ArrayList<>();
        this.internships = new ArrayList<>();
    }
}
