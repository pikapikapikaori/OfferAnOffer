CREATE DATABASE IF NOT EXISTS resume;

USE resume;

CREATE TABLE IF NOT EXISTS Resume (
                                    resumeId INT PRIMARY KEY AUTO_INCREMENT,
                                    id VARCHAR(255),
                                    resumeContent BLOB,
                                    jobId VARCHAR(255),
                                    resumeName VARCHAR(255),
                                    jobName VARCHAR(255),
                                    name VARCHAR(255)
);


