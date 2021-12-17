package com.oaoffer.loginservice.dao;

import com.oaoffer.loginservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    protected UserRepository userRepository;

    @Test
    public void get_have_user_test() {
        User res = userRepository.get_user("test@test.com", "test");
        assertEquals("test@test.com", res.getEmail());
    }

    @Test
    public void get_havent_user_test() {
        User res = userRepository.get_user("testregister@test.com", "test");
        System.out.println(res);
        // assertEquals("test@test.com", res.getEmail());
    }
}