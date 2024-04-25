package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long commentid;
    private Long taskid;
    private Long userid;
    private String commentcontent;
    private LocalDate dateadded;

    public Comment() {
    }

    public Comment(Long commentid, Long taskid, Long userid, String commentcontent, LocalDate dateadded) {
        this.commentid = commentid;
        this.taskid = taskid;
        this.userid = userid;
        this.commentcontent = commentcontent;
        this.dateadded = dateadded;
    }

    public Comment(Long taskid, Long userid, String commentcontent, LocalDate dateadded) {
        this.taskid = taskid;
        this.userid = userid;
        this.commentcontent = commentcontent;
        this.dateadded = dateadded;
    }
}
