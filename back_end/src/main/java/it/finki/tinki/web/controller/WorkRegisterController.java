package it.finki.tinki.web.controller;

import it.finki.tinki.model.Users.Account;
import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.dto.register.work.InternshipRegisterDTO;
import it.finki.tinki.model.dto.register.work.JobRegisterDTO;
import it.finki.tinki.model.dto.register.work.ProjectRegisterDTO;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.model.enumerator.AccountType;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/register/work")
public class WorkRegisterController {

    WorkService workService;

    public WorkRegisterController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping("/job")
    public JobResponseDTO registerJob(@RequestBody JobRegisterDTO body){

        if(body.getType() != AccountType.USER){
            Job j = this.workService.insertJob(body.getTitle(),
                    body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsRequired(), body.getType());
            return new JobResponseDTO(j);
        }

        return new JobResponseDTO();
    }

    @PostMapping("/internship")
    public InternshipResponseDTO registerInternship(@RequestBody InternshipRegisterDTO body){
        if(body.getType() == AccountType.COMPANY){
            Internship j = this.workService.insertInternship(body.getTitle(),
                    body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsTrained(), body.getOpenSpots(), body.getType());
            return new InternshipResponseDTO(j);
        }

        return new InternshipResponseDTO();
    }

    @PostMapping("/project")
    public ProjectResponseDTO registerProject(@RequestBody ProjectRegisterDTO body){

        if(body.getType() == AccountType.TEAM){
            Project j = this.workService.insertProject(body.getTitle(),
                    body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsRequired(), body.getValidUntil(), body.getType());
            return new ProjectResponseDTO(j);
        }

        return new ProjectResponseDTO();
    }
}
