CREATE DATABASE IF NOT EXISTS process;

USE process;

CREATE TABLE IF NOT EXISTS User (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    identity VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Apply (
    resumeId VARCHAR(255) PRIMARY KEY,
    id VARCHAR(255),
    status VARCHAR(255),
    resumeStatus VARCHAR(255),
    interviewStatus VARCHAR(255),
    evaluation VARCHAR(255),
    offerStatus VARCHAR(255)
);
