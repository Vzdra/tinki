package it.finki.tinki.web.controller;

import it.finki.tinki.model.dto.response.work.InternshipResponseDTO;
import it.finki.tinki.model.dto.response.work.JobResponseDTO;
import it.finki.tinki.model.dto.response.work.ProjectResponseDTO;
import it.finki.tinki.service.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/search")
public class SearchController {

    WorkService workService;

    public SearchController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping(path = "/job")
    public List<JobResponseDTO> jobRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextJobSearch(text);
    }

    @GetMapping(path = "/internship")
    public List<InternshipResponseDTO> internshipRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextInternshipSearch(text);
    }

    @GetMapping(path = "/project")
    public List<ProjectResponseDTO> projectRes(@RequestParam(name = "text") String text){
        return this.workService.fullTextProjectSearch(text);
    }
}
