package com.oaoffer.processservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SystemUtilControllerTest {
    @Autowired
    protected SystemUtilController systemUtilController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void add_user() throws Exception {
        String resstr = mockMvc.perform(
                        post("/system/add_user")
                                .param("id", "3")
                                .param("name", "testregister@test.com")
                                .param("identity", "interviewee")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        assertEquals(0, res.getInteger("code"));
    }

    @Test
    void add_apply() throws Exception {
        String resstr = mockMvc.perform(
                        post("/system/add_apply")
                                .param("id", "3")
                                .param("resumeId", "1")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        assertEquals(1, res.getInteger("code"));
    }
}