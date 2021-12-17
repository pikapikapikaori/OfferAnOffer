package com.oaoffer.processservice.controller;
import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.service.SystemUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/system", produces = { "application/json;charset=UTF-8" })
public class SystemUtilController {
    private SystemUtilService systemUtilService;

    @Autowired
    public SystemUtilController(SystemUtilService systemUtilService) {
        this.systemUtilService = systemUtilService;
    }

    @PostMapping("/add_user")
    public JSONObject add_user(String id, String name, String identity) {
        return systemUtilService.add_user(id, name, identity);
    }

    @PostMapping("/add_apply")
    public JSONObject add_apply(String id, String resumeId) {
        return systemUtilService.add_apply(id, resumeId);
    }
}
