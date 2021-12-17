package com.oaoffer.processservice.dao;

import com.oaoffer.processservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into User values(:id, :name, :identity)", nativeQuery = true)
    int insert_user(@Param("id") String id,
                    @Param("name") String name,
                    @Param("identity") String identity);

    @Query("select u.name from User u where u.id = :id")
    String get_name(@Param("id") String id);

    @Query("select u from User u where u.id = :id")
    User get_user(@Param("id") String id);
}