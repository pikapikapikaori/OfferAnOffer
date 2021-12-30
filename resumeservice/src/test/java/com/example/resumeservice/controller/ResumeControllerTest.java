package com.example.resumeservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.resumeservice.dao.ResumeRepository;
import com.example.resumeservice.entity.Resume;
import org.junit.jupiter.api.BeforeEach;
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
class ResumeControllerTest {
    @Autowired
    protected ResumeController resumeController;

    @Autowired
    protected ResumeRepository resumeRepository;

    @Autowired
    protected MockMvc mockMvc;



    @Test
    void get_personal_center_data_test() throws Exception{
        String resstr = mockMvc.perform(
                get("/user/upload-resume/get_personal_center_data")
                        .param("id", "234")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        JSONArray res = JSON.parseArray(resstr);

        System.out.println(res);

    }

    @Test
    void get_pdata_test() {
        JSONArray res = resumeController.get_personal_center_data("234");

        System.out.println(res);
    }

    @Test
    void get_hr_personal_center_data_test() throws Exception {
        String resstr = mockMvc.perform(
                get("/user/upload-resume/get_hr_personal_center_data")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        JSONArray res = JSON.parseArray(resstr);

        System.out.println(res);
    }

    @Test
    void update_resumeContent() {

    }


    @Test
    void add_apply_on_resume () throws Exception {
        String resstr = mockMvc.perform(
                post("/resume/add_apply_on_resume")
                        .param("id", "333")
                        .param("resumeId", "222")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        JSONObject res = JSON.parseObject(resstr);

        System.out.println(res);
        assertEquals(1, res.getInteger("code"));
    }
}
