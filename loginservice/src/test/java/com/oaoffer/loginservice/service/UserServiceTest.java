package com.oaoffer.loginservice.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    protected UserService userService;

    @Test
    void have_register() {
        JSONObject res = userService.register("test@test.com", "test");
        assertEquals(0, res.getInteger("code"));
    }

    @Test
    void haven_register() {
        JSONObject res = userService.register("testregister@test.com", "test");
        assertEquals(1, res.getInteger("code"));
    }
}