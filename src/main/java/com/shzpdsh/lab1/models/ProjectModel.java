package com.shzpdsh.lab1.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectModel {
    
    private Long id;
    
    private String name;
    
    private String description;
    
    private Date start;
    
    private Date end;
}