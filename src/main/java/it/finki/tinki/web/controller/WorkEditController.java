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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/edit/work")
public class WorkEditController {

    WorkService workService;

    public WorkEditController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping("/job/{id}")
    public JobResponseDTO editJob(@PathVariable Long id,
                                  @RequestBody JobRegisterDTO body){

        if(body.getAccountId().equals(this.workService.getJobById(id).getAccount().getId())) {
            Job k = this.workService.editJob(id, body.getTitle(), body.getDescription(), body.getSalary());
            return new JobResponseDTO(k);
        }
        return null;
    }

    @PostMapping("/internship/{id}")
    public InternshipResponseDTO editInternship(@PathVariable Long id,
                                                @RequestBody InternshipRegisterDTO body){

        if(body.getAccountId().equals(this.workService.getJobById(id).getAccount().getId())){
            Internship k = this.workService.editInternship(id, body.getTitle(), body.getDescription(), body.getSalary(), body.getOpenSpots());
            return new InternshipResponseDTO(k);
        }
        return null;
    }

    @PostMapping("/project/{id}")
    public ProjectResponseDTO editProject(@PathVariable Long id,
                                          @RequestBody ProjectRegisterDTO body){

        if(body.getAccountId().equals(this.workService.getJobById(id).getAccount().getId())) {
            Project k = this.workService.editProject(id, body.getTitle(), body.getDescription(), body.getSalary());
            return new ProjectResponseDTO(k);
        }
        return null;
    }
}
