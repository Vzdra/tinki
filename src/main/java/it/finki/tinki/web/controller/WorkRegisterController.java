package it.finki.tinki.web.controller;

import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.dto.register.work.InternshipRegisterDTO;
import it.finki.tinki.model.dto.register.work.JobRegisterDTO;
import it.finki.tinki.model.dto.register.work.ProjectRegisterDTO;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/register/work")
public class WorkRegisterController {

    WorkService workService;

    public WorkRegisterController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping("/job")
    public JobResponseDTO registerJob(@RequestBody JobRegisterDTO body){

        Job j = this.workService.insertJob(body.getTitle(),
                body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsRequired(), body.getType());

        return new JobResponseDTO(j);
    }

    @PostMapping("/internship")
    public InternshipResponseDTO registerInternship(@RequestBody InternshipRegisterDTO body){

        Internship j = this.workService.insertInternship(body.getTitle(),
                body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsTrained(), body.getOpenSpots(), body.getType());

        return new InternshipResponseDTO(j);
    }

    @PostMapping("/project")
    public ProjectResponseDTO registerProject(@RequestBody ProjectRegisterDTO body){

        Project j = this.workService.insertProject(body.getTitle(),
                body.getDescription(), body.getAccountId(), body.getSalary(), body.getSkillsRequired(), body.getValidUntil(), body.getType());

        return new ProjectResponseDTO(j);
    }
}
