package it.finki.tinki.model.dto;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Jobs.Job;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class JobResponseDTO {
    Long id;
    String title;
    String description;
    int salary;
    String accountEmail;
    String accountName;
    AccountType accountType;
    Address accountAddress;
    int members;

    public JobResponseDTO(Job job){
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.salary = job.getSalary();
        this.accountEmail = job.getAccount().getEmail();
        this.accountName = job.getAccount().getName();
        this.accountType = job.getAccount().getAccountType();
        if(job.getAccount().getAccountType()==AccountType.COMPANY){
            this.accountAddress = ((Company) job.getAccount()).getAddress();
            this.members = 0;
        }else{
            this.members = ((Team) job.getAccount()).getMembers();
            this.accountAddress = null;
        }
    }
}
