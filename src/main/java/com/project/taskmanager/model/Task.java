package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long taskid;
    private Long projectid;
    private String taskname;
    private String description;
    private Long priority;
    private LocalDate creationdate;
    private LocalDate duedate;
    private Long status;

    public Task() {
    }

    public Task(Long taskid, Long projectid, String taskname, String description, Long priority, LocalDate creationdate, LocalDate duedate, Long status) {
        this.taskid = taskid;
        this.projectid = projectid;
        this.taskname = taskname;
        this.description = description;
        this.priority = priority;
        this.creationdate = creationdate;
        this.duedate = duedate;
        this.status = status;
    }

    public Task(Long projectid, String taskname, String description, Long priority, LocalDate creationdate, LocalDate duedate, Long status) {
        this.projectid = projectid;
        this.taskname = taskname;
        this.description = description;
        this.priority = priority;
        this.creationdate = creationdate;
        this.duedate = duedate;
        this.status = status;
    }
}
