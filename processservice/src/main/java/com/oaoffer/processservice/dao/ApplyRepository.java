package com.oaoffer.processservice.dao;

import com.oaoffer.processservice.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ApplyRepository extends JpaRepository<Apply, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into Apply values(:resumeId, :id, :status, :resumeStatus, :interviewStatus, :evaluation, :offerStatus)", nativeQuery = true)
    int insert_apply(@Param("resumeId") String resumeId,
                     @Param("id") String id,
                     @Param("status") String status,
                     @Param("resumeStatus") String resumeStatus,
                     @Param("interviewStatus") String interviewStatus,
                     @Param("evaluation") String evaluation,
                     @Param("offerStatus") String offerStatus);

    @Query("select a.status from Apply a where a.id = :id and a.resumeId = :resumeId")
    String get_status(@Param("id") String id, @Param("resumeId") String resumeId);

    @Query("select a.resumeStatus from Apply a where a.id = :id and a.resumeId = :resumeId")
    String get_resumeStatus(@Param("id") String id, @Param("resumeId") String resumeId);

    @Query("select a.interviewStatus from Apply a where a.id = :id and a.resumeId = :resumeId")
    String get_interviewStatus(@Param("id") String id, @Param("resumeId") String resumeId);

    @Query("select a.offerStatus from Apply a where a.id = :id and a.resumeId = :resumeId")
    String get_offerStatus(@Param("id") String id, @Param("resumeId") String resumeId);

    @Query("select a.evaluation from Apply a where a.id = :id and a.resumeId = :resumeId")
    String get_evaluation(@Param("id") String id, @Param("resumeId") String resumeId);

    @Query("select a from Apply a where a.id = :id and a.resumeId = :resumeId")
    Apply get_apply(@Param("id") String id, @Param("resumeId") String resumeId);

    @Transactional
    @Modifying
    @Query("update Apply a set a.status = :status where a.id = :id and a.resumeId = :resumeId")
    int update_status(@Param("id") String id, @Param("resumeId") String resumeId, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("update Apply a set a.resumeStatus = :status where a.id = :id and a.resumeId = :resumeId")
    int update_resumeStatus(@Param("id") String id, @Param("resumeId") String resumeId, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("update Apply a set a.interviewStatus = :status where a.id = :id and a.resumeId = :resumeId")
    int update_interviewStatus(@Param("id") String id, @Param("resumeId") String resumeId, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("update Apply a set a.offerStatus = :status where a.id = :id and a.resumeId = :resumeId")
    int update_offerStatus(@Param("id") String id, @Param("resumeId") String resumeId, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("update Apply a set a.evaluation = :evaluation where a.id = :id and a.resumeId = :resumeId")
    int update_evaluation(@Param("id") String id, @Param("resumeId") String resumeId, @Param("evaluation") String evaluation);

}