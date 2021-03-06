package com.sample.blog.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 500, unique = true)
    private String username;

    @Column(length = 400)
    private String password;

    @Column(length = 500, unique = true)
    private String email;

    @Column(length = 500)
    private String token;

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }
}