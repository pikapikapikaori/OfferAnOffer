DROP DATABASE IF EXISTS process;
CREATE DATABASE process;

USE process;

DROP TABLE IF EXISTS User;
CREATE TABLE User (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    identity VARCHAR(255)
);

DROP TABLE IF EXISTS Apply;
CREATE TABLE Apply (
    resumeId VARCHAR(255) PRIMARY KEY,
    id VARCHAR(255),
    status VARCHAR(255),
    resumeStatus VARCHAR(255),
    interviewStatus VARCHAR(255),
    evaluation VARCHAR(255),
    offerStatus VARCHAR(255)
);
