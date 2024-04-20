package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long projectid;
    private String projectname;
    private String description;
    private LocalDate startdate;
    private LocalDate enddate;

    public Project() {
    }

    public Project(Long projectid, String projectname, String description, LocalDate startdate, LocalDate enddate) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Project(String projectname, String description, LocalDate startdate, LocalDate enddate) {
        this.projectname = projectname;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Project(String projectname) {
        this.projectname = projectname;
        this.description = "description";
        this.enddate = LocalDate.now();
        this.startdate = LocalDate.now();
    }
}
