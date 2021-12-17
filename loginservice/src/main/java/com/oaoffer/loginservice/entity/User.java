package com.oaoffer.loginservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String identity;
}