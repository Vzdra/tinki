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
    Long accountId;
    AccountType accountType;
    String error;

    WorkResponseDTO(){}

    WorkResponseDTO(String item){
        this.error = "Error registering " + item;
    }

    WorkResponseDTO(Long id, String title, String description, int salary, Account account){
        this.error = null;
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.accountId = account.getId();
        this.accountEmail = account.getEmail();
        this.accountName = account.getName();
        this.accountType = account.getAccountType();
    }
}
