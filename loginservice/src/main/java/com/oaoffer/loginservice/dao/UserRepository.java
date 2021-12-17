package com.oaoffer.loginservice.dao;

import com.oaoffer.loginservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    User get_user(@Param("email") String email,@Param("password") String password);

}