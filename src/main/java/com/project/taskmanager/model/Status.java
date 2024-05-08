package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long statusid;
    private String statusname;
    public Status() {
    }
    public Status(Long statusid, String statusname) {
        this.statusid = statusid;
        this.statusname = statusname;
    }
    public Status(String statusname) {
        this.statusname = statusname;
    }
}
