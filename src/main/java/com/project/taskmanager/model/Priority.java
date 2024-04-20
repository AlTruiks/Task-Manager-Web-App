package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "priorities")
public class Priority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long priorityid;
    private String priorityname;

    public Priority() {
    }

    public Priority(Long priorityid, String priorityname) {
        this.priorityid = priorityid;
        this.priorityname = priorityname;
    }

    public Priority(String priorityname) {
        this.priorityname = priorityname;
    }
}
