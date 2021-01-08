package it.finki.tinki.model.dto.response.work;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.enumerator.AccountType;
import lombok.Data;

@Data
public class WorkResponseDTO {
    Long id;
    String title;
    String description;
    int salary;
    String accountEmail;
    String accountName;
    AccountType accountType;

    WorkResponseDTO(){}

    WorkResponseDTO(Long id, String title, String description, int salary, Account account){
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.accountEmail = account.getEmail();
        this.accountName = account.getName();
        this.accountType = account.getAccountType();
    }
}
