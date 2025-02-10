package com.shzpdsh.lab1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.shzpdsh.lab1.models.ProjectModel;
import com.shzpdsh.lab1.services.ProjectService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectModel createProjectModel(@RequestBody ProjectModel projectModel) {
        return projectService.createProjectModel(projectModel);
    }

    @PutMapping("/{id}")
    public ProjectModel updateProjectModel(@PathVariable Long id, @RequestBody ProjectModel projectModel) {
        return projectService.updateProjectModel(id, projectModel);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }

    @GetMapping("/{id}")
    public ProjectModel getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping
    public List<ProjectModel> getProjectsByDateRange(@RequestParam Date start1, @RequestParam Date end1) {
        return projectService.getProgectsByDateRange(start1, end1);
    }
    
    @GetMapping("/all")
    public List<ProjectModel> getAllProgects() {
        return projectService.getAllProgects();
    }
}
