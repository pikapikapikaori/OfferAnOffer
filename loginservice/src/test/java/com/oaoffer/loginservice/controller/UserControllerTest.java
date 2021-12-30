package com.oaoffer.loginservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    protected UserController userController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void have_register() throws Exception {
        String resstr = mockMvc.perform(
                        post("/auth/register")
                                .param("email", "testregister@test.com")
                                .param("password", "test")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        assertEquals(1, res.getInteger("code"));
    }

    @Test
    void haven_register() {
    }

    @Test
    void register_add_user() throws Exception {
        String resstr = mockMvc.perform(
                        post("/auth/register_add_user")
                                .param("id", "3")
                                .param("email", "testregister@test.com")
                                .param("identity", "interviewee")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        System.out.println(res);
        assertEquals(1, res.getInteger("code"));
    }

    @Test
    void login() throws Exception {
        String resstr = mockMvc.perform(
                        get("/auth/login")
                                .param("email", "testlogin@test.com")
                                .param("identity", "test")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        System.out.println(res);
        assertEquals(0, res.getInteger("code"));
    }
}