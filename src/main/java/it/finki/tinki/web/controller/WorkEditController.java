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
    public Map<String, String> editJob(@PathVariable Long id,
                                       @RequestBody JobRegisterDTO body){

        Map<String, String> response = new HashMap<>();

        if(body.getAccountId().equals(this.workService.getJobById(id).getAccount().getId())) {
            Job k = this.workService.editJob(id, body.getTitle(), body.getDescription(), body.getSalary());
            if(k!=null){
                response.put("success", "Job edited successfully!");
                return response;
            }
        }

        response.put("error", "Internship edit failed!");
        return response;
    }

    @PostMapping("/internship/{id}")
    public Map<String, String> editInternship(@PathVariable Long id,
                                                @RequestBody InternshipRegisterDTO body){

        Map<String, String> response = new HashMap<>();

        if(body.getAccountId().equals(this.workService.getInternshipById(id).getAccount().getId())){
            Internship k = this.workService.editInternship(id, body.getTitle(), body.getDescription(), body.getSalary(), body.getOpenSpots());
            if(k!=null){
                response.put("success", "Internship edited successfully!");
                return response;
            }
        }

        response.put("error", "Internship edit failed!");
        return response;
    }

    @PostMapping("/project/{id}")
    public Map<String, String> editProject(@PathVariable Long id,
                                          @RequestBody ProjectRegisterDTO body){

        Map<String, String> response = new HashMap<>();

        if(body.getAccountId().equals(this.workService.getProjectById(id).getAccount().getId())) {
            Project k = this.workService.editProject(id, body.getTitle(), body.getDescription(), body.getSalary());
            if(k!=null){
                response.put("success", "Project edited successfully!");
                return response;
            }
        }

        response.put("error", "Project edit failed!");
        return response;
    }
}
