package it.finki.tinki.model.dto.response.account;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyResponseDTO extends LoginResponseDTO {
    private Address address;
    private List<JobResponseDTO> jobs;
    private List<InternshipResponseDTO> internships;

    public CompanyResponseDTO(){
        super();
        this.jobs = new ArrayList<>();
        this.internships = new ArrayList<>();
    }
}
