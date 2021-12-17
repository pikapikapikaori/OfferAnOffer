package com.oaoffer.processservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/hr", produces = { "application/json;charset=UTF-8" })
public class HRController {
    private HRService hrService;

    @Autowired
    public HRController(HRService hrService) {
        this.hrService = hrService;
    }

    // 需要在简历模块调用本方法，使面试通知发送后更新此状态
    @PostMapping("update_status")
    public JSONObject update_status(String id, String resumeId, String status) {
        return hrService.update_status(id, resumeId, status);
    }

    // 需要在简历模块调用本方法，使简历筛选完成后更新此状态
    @PostMapping("update_resumeStatus")
    public JSONObject update_resumeStatus(String id, String resumeId, String resumeStatus) {
        return hrService.update_resumeStatus(id, resumeId, resumeStatus);
    }

    @PostMapping("update_interviewStatus")
    public JSONObject update_interviewStatus(String id, String resumeId, String interviewStatus) {
        return hrService.update_interviewStatus(id, resumeId, interviewStatus);
    }

    // 用作发offer的方法
    @PostMapping("update_offerStatus")
    public JSONObject update_offerStatus(String id, String resumeId, String offerStatus) {
        return hrService.update_offerStatus(id, resumeId, offerStatus);
    }

    // 填写或修改面试评价，调用本方法自动更新interviewStatus
    @PostMapping("update_evaluation")
    public JSONObject update_evaluation(String id, String resumeId, String evaluation) {
        return hrService.update_evaluation(id, resumeId, evaluation);
    }
}
