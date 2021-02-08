package it.finki.tinki.service;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.dto.response.account.CompanyResponseDTO;
import it.finki.tinki.model.dto.response.account.TeamResponseDTO;
import it.finki.tinki.model.dto.response.account.UserResponseDTO;

public interface BuilderService {
    UserResponseDTO buildUserResponseDTO(Account a1);
    CompanyResponseDTO buildCompanyResponseDTO(Account a1);
    TeamResponseDTO buildTeamResponseDTO(Account a1);
}
