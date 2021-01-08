package it.finki.tinki.model.dto;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Jobs.Internship;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class InternshipResponseDTO {
    Long id;
    String title;
    String description;
    int salary;
    int openSpots;
    String accountEmail;
    String accountName;
    AccountType accountType;
    Address accountAddress;

    public InternshipResponseDTO(Internship internship){
        this.id = internship.getId();
        this.title = internship.getTitle();
        this.description = internship.getDescription();
        this.salary = internship.getSalary();
        this.openSpots = internship.getOpenSpots();
        this.accountEmail = internship.getAccount().getEmail();
        this.accountName = internship.getAccount().getName();
        this.accountType = internship.getAccount().getAccountType();
        this.accountAddress = ((Company) internship.getAccount()).getAddress();
    }
}
