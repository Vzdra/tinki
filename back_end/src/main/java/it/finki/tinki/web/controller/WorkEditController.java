package it.finki.tinki.web.controller;

import it.finki.tinki.model.Work.Internship;
import it.finki.tinki.model.Work.Job;
import it.finki.tinki.model.Work.Project;
import it.finki.tinki.model.dto.edit.work.InternshipEditDTO;
import it.finki.tinki.model.dto.edit.work.WorkEditDTO;
import it.finki.tinki.model.dto.register.work.InternshipRegisterDTO;
import it.finki.tinki.model.dto.register.work.JobRegisterDTO;
import it.finki.tinki.model.dto.register.work.ProjectRegisterDTO;
import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.service.WorkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
                                  @RequestBody WorkEditDTO body){

        Job j = this.workService.getJobById(id);

        if(body.getAccountId().equals(j.getAccount().getId())) {
            Job k = this.workService.editJob(id, body.getTitle(), body.getDescription(), body.getSalary());
            if(k!=null){
                return new JobResponseDTO(k);
            }
        }

        return new JobResponseDTO();
    }

    @PostMapping("/internship/{id}")
    public InternshipResponseDTO editInternship(@PathVariable Long id,
                                                @RequestBody InternshipEditDTO body){

        Internship i = this.workService.getInternshipById(id);

        if(body.getAccountId().equals(i.getAccount().getId())){
            Internship k = this.workService.editInternship(id, body.getTitle(), body.getDescription(), body.getSalary(), body.getOpenSpots());
            if(k!=null){
                return new InternshipResponseDTO(k);
            }
        }

        return new InternshipResponseDTO();
    }

    @PostMapping("/project/{id}")
    public ProjectResponseDTO editProject(@PathVariable Long id,
                                          @RequestBody WorkEditDTO body){

        Project p = this.workService.getProjectById(id);

        if(body.getAccountId().equals(p.getAccount().getId())) {
            Project k = this.workService.editProject(id, body.getTitle(), body.getDescription(), body.getSalary());
            if(k!=null){
                return new ProjectResponseDTO(k);
            }
        }

        return new ProjectResponseDTO();
    }
}
