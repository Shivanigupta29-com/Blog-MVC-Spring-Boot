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

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 250)
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