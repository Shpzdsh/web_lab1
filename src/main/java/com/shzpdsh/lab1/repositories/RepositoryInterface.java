package com.shzpdsh.lab1.repositories;

import java.util.Date;
import java.util.List;

import com.shzpdsh.lab1.models.ProjectModel;

public interface RepositoryInterface {

    ProjectModel save(ProjectModel ProjectModel);
    
    ProjectModel update(Long id, ProjectModel projectModel);
    
    void delete(Long id);
    
    ProjectModel findById(Long id);
    
    List<ProjectModel> findByDateRange(Date start, Date end);
    
    List<ProjectModel> findAll();
}
