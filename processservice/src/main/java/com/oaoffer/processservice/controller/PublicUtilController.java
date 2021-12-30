package com.oaoffer.processservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.constant.Constant;
import com.oaoffer.processservice.service.PublicUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping(value = "/public", produces = { "application/json;charset=UTF-8" })
public class PublicUtilController {
    private PublicUtilService publicUtilService;
    private RestTemplate restTemplate;

    @Autowired
    public PublicUtilController(PublicUtilService publicUtilService, RestTemplate restTemplate) {
        this.publicUtilService = publicUtilService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("get_status")
    public JSONObject get_status(String id, String resumeId) {
        return publicUtilService.get_status(id, resumeId);
    }

    @GetMapping("get_resumeStatus")
    public JSONObject get_resumeStatus(String id, String resumeId) {
        return publicUtilService.get_resumeStatus(id, resumeId);
    }

    @GetMapping("get_interviewStatus")
    public JSONObject get_interviewStatus(String id, String resumeId) {
        return publicUtilService.get_interviewStatus(id, resumeId);
    }

    @GetMapping("get_offerStatus")
    public JSONObject get_offerStatus(String id, String resumeId) {
        return publicUtilService.get_offerStatus(id, resumeId);
    }

    @GetMapping("get_evaluation")
    public JSONObject get_evaluation(String id, String resumeId) {
        return publicUtilService.get_evaluation(id, resumeId);
    }

    @GetMapping("get_name")
    public JSONObject get_name(String id) {
        return publicUtilService.get_name(id);
    }

    @GetMapping("get_user")
    public JSONObject get_user(String id) {
        return publicUtilService.get_user(id);
    }

    // 调用简历模块获取面试通知中的信息
    @GetMapping("get_interview_time")
    public JSONObject get_interviewInfo(String id) {
        String url = "";

        return restTemplate.getForObject(Constant.baseUrl + url, JSONObject.class);
    }
}
