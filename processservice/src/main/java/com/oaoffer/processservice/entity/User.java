package com.oaoffer.processservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class User {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String identity; // interviewee, hr
}
