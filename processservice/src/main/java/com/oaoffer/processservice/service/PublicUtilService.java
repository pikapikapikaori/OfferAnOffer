package com.oaoffer.processservice.service;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.dao.ApplyRepository;
import com.oaoffer.processservice.dao.UserRepository;
import com.oaoffer.processservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicUtilService {
    private UserRepository userRepository;
    private ApplyRepository applyRepository;

    @Autowired
    public PublicUtilService(UserRepository userRepository, ApplyRepository applyRepository) {
        this.userRepository = userRepository;
        this.applyRepository = applyRepository;
    }

    public JSONObject get_status(String id, String resumeId) {
        JSONObject obj = new JSONObject();
        String res = applyRepository.get_status(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("status", res);

        return obj;
    }

    public JSONObject get_resumeStatus(String id, String resumeId) {
        JSONObject obj = new JSONObject();
        String res = applyRepository.get_resumeStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("resumeStatus", res);

        return obj;
    }

    public JSONObject get_interviewStatus(String id, String resumeId) {
        JSONObject obj = new JSONObject();
        String res = applyRepository.get_interviewStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("interviewStatus", res);

        return obj;
    }

    public JSONObject get_offerStatus(String id, String resumeId) {
        JSONObject obj = new JSONObject();
        String res = applyRepository.get_offerStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("offerStatus", res);

        return obj;
    }

    public JSONObject get_evaluation(String id, String resumeId) {
        JSONObject obj = new JSONObject();
        String res = applyRepository.get_evaluation(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("evaluation", res);

        return obj;
    }

    public JSONObject get_name(String id) {
        JSONObject obj = new JSONObject();
        String res = userRepository.get_name(id);

        obj.put("id", id);
        obj.put("name", res);

        return obj;
    }

    public JSONObject get_user(String id) {
        JSONObject obj = new JSONObject();
        User res = userRepository.get_user(id);

        obj.put("name", res.getName());
        obj.put("id", res.getId());
        obj.put("identity", res.getIdentity());

        return obj;
    }
}
