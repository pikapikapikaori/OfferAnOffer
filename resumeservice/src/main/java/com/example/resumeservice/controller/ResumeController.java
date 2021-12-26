package com.example.resumeservice.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.resumeservice.Constant.Constant;
import com.example.resumeservice.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/resume", produces = { "application/json;charset=UTF-8" })
public class ResumeController {
    private ResumeService resumeService;
    private RestTemplate restTemplate;

    @Autowired
    public ResumeController(ResumeService resumeService, RestTemplate restTemplate) {
        this.resumeService = resumeService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/update_resumeContent")
    public JSONObject update_resumeContent(String id, String resumeId, byte[] resumeContent) {
        return resumeService.update_resumeContent(id, resumeId, resumeContent);
    }

    @PostMapping("/add_resume")
    public JSONObject add_resume(String id, byte[] resumeContent, String jobId) {
        JSONObject obj = resumeService.add_resume(id, resumeContent, jobId);

        JSONObject applyObj = add_apply_on_resume(id, obj.getString("resumeId"));

        return obj;
    }

    @PostMapping("add_apply_on_resume")
    public JSONObject add_apply_on_resume(String id, String resumeId){
        String url = "/system/add_apply";

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("id", id);
        requestEntity.add("resumeId",resumeId);

        return restTemplate.postForObject(Constant.baseProcessUrl + url, requestEntity, JSONObject.class);
    }
}
