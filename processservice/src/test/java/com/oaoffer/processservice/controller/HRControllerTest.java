package com.oaoffer.processservice.controller;

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
class HRControllerTest {
    @Autowired
    protected HRController hrController;

    @Autowired
    protected  MockMvc mockMvc;

    @Test
    void update_status_test() throws  Exception {
        String resstr = mockMvc.perform(
                        post("/hr/update_status")
                                .param("id", "9")
                                .param("resumeId", "1")
                                .param("status", "interview")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JSONObject res = JSON.parseObject(resstr);

        assertEquals(0, res.getInteger("code"));
    }

}