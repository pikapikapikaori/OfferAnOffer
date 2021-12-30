package com.example.resumeservice.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ResumeServiceTest {
    @Autowired
    protected ResumeService resumeService;

    @Test
    void add_resume_name() {
        JSONObject res = resumeService.add_resume_name("gaoruihan.pdf", "111", "1");
        System.out.println(res);
    }
}
