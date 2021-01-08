package it.finki.tinki.model.dto;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Jobs.Project;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class ProjectResponseDTO {
    Long id;
    String title;
    String description;
    int salary;
    String accountEmail;
    String accountName;
    AccountType accountType;
    int members;

    public ProjectResponseDTO(Project project){
        this.id = project.getId();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.salary = project.getSalary();
        this.accountEmail = project.getAccount().getEmail();
        this.accountName = project.getAccount().getName();
        this.accountType = project.getAccount().getAccountType();
        this.members = ((Team) project.getAccount()).getMembers();
    }
}
