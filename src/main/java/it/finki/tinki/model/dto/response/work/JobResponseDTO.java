package it.finki.tinki.model.dto.response.work;

import it.finki.tinki.model.Address;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Users.Company;
import it.finki.tinki.model.Users.Team;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class JobResponseDTO extends WorkResponseDTO {
    Address accountAddress;
    int members;

    public JobResponseDTO(Job job){
        super(job.getId(), job.getTitle(), job.getDescription(), job.getSalary(), job.getAccount());
        if(job.getAccount().getAccountType()==AccountType.COMPANY){
            this.accountAddress = ((Company) job.getAccount()).getAddress();
            this.members = 0;
        }else{
            this.members = ((Team) job.getAccount()).getMembers();
            this.accountAddress = null;
        }
    }
}
