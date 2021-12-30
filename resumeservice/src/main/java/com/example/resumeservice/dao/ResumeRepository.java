package com.example.resumeservice.dao;

import com.example.resumeservice.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ResumeRepository extends JpaRepository<Resume, String> {
    //查看简历
    @Query("select r.resumeContent from Resume r where r.id = :id and r.resumeId = :resumeId")
    byte[] get_resumeContent(@Param("id") String id, @Param("resumeId") String resumeId);

    //获取resumeId
    @Query("select r.resumeId from Resume r where r.id = :id")
    ArrayList<String> get_all_resumeId(@Param("id") String id);

    @Query("select r.resumeId from Resume r where r.id = :id and r.jobId = :jobId")
    String get_resumeId(@Param("id") String id, @Param("jobId") String jobId);

    @Query("select r from Resume r where r.id = :id and r.jobId = :jobId")
    Resume get_resume(@Param("id") String id, @Param("jobId") String jobId);

    @Query("select r from Resume r where r.id = :id")
    ArrayList<Resume> get_personal_center_data(@Param("id") String id);

    @Query("select r from Resume r")
    ArrayList<Resume> get_hr_personal_center_data();



    //上传简历
    @Transactional
    @Modifying
    @Query("update Resume r set r.resumeContent = :resumeContent where r.id = :id and r.resumeId = :resumeId ")
    int update_resumeContent(@Param("id") String id, @Param("resumeId") String resumeId, @Param("resumeContent") byte[] resumeContent);

    //上传简历名称
    @Transactional
    @Modifying
    @Query("update Resume r set r.resumeName = :resumeName where r.id = :id and r.resumeId = :resumeId")
    int update_resumeName(@Param("resumeName") String resumeName, @Param("id") String id, @Param("resumeId") int resumeId);

}
