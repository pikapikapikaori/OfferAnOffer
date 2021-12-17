package com.oaoffer.processservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Apply {
    @Id
    @Column
    private String resumeId;

    @Column
    private String id;

    @Column
    private String status; // resume, interview, offer, finished

    @Column
    private String resumeStatus; // waiting, accepted, refused

    @Column
    private String interviewStatus; // waiting, completed

    @Column
    private String evaluation;

    @Column
    private String offerStatus; // waiting, hired, rejected
}
