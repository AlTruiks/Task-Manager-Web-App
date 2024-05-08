package com.project.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (
            strategy = GenerationType.IDENTITY
    )
    private  Long userid;
    private String firstname;
    private String lastname;
    private String nickname;
    private String password;
    public User() {
    }
    public User(Long userid, String firstname, String lastname, String nickname, String password) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.password = password;
    }
    public User(String firstname, String lastname, String nickname, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.password = password;
    }
}
