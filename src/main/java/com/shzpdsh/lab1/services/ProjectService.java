package com.shzpdsh.lab1.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shzpdsh.lab1.models.ProjectModel;
import com.shzpdsh.lab1.repositories.ProjectRepository;

@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;

    public ProjectService (ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectModel createProjectModel(ProjectModel projectModel) {
        return projectRepository.save(projectModel);
    }

    public ProjectModel updateProjectModel(Long id, ProjectModel projectModel) {
        return projectRepository.update(id, projectModel);
    }

    public void delete(long id) {
        projectRepository.delete(id);
    }

    public ProjectModel getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public List<ProjectModel> getProgectsByDateRange(Date start1, Date end1) {
        return projectRepository.findByDateRange(start1, end1);
    }

    public List<ProjectModel> getAllProgects() {
        return projectRepository.findAll();  
    }
}
