package com.example.resumeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Resume {
    @Id
    @Column
    private String resumeId;

    @Column
    private String id;

    @Lob
    @Basic
    @Column
    private byte[] resumeContent;

    @Column
    private String jobId;

}
