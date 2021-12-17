package com.oaoffer.processservice.service;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.dao.ApplyRepository;
import com.oaoffer.processservice.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRService {
    private ApplyRepository applyRepository;

    @Autowired
    public HRService(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }

    public JSONObject update_status(String id, String resumeId, String status) {
        JSONObject obj = new JSONObject();

        int code = applyRepository.update_status(id, resumeId, status);

        String updated = applyRepository.get_status(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("status", updated);
        obj.put("code", code);

        return obj;
    }

    public JSONObject update_resumeStatus(String id, String resumeId, String resumeStatus) {
        JSONObject obj = new JSONObject();

        int code = applyRepository.update_resumeStatus(id, resumeId, resumeStatus);

        String updated = applyRepository.get_resumeStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("resumeStatus", updated);
        obj.put("code", code);

        return obj;
    }

    public JSONObject update_interviewStatus(String id, String resumeId, String interviewStatus) {
        JSONObject obj = new JSONObject();

        int code = applyRepository.update_interviewStatus(id, resumeId, interviewStatus);

        String updated = applyRepository.get_interviewStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("interviewStatus", updated);
        obj.put("code", code);

        return obj;
    }

    public JSONObject update_offerStatus(String id, String resumeId, String offerStatus) {
        JSONObject obj = new JSONObject();

        int code = applyRepository.update_offerStatus(id, resumeId, offerStatus);

        String updated = applyRepository.get_offerStatus(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("offerStatus", updated);obj.put("code", code);


        return obj;
    }

    public JSONObject update_evaluation(String id, String resumeId, String evaluation) {
        JSONObject obj = new JSONObject();

        int code = applyRepository.update_evaluation(id, resumeId, evaluation);

        String updated = applyRepository.get_evaluation(id, resumeId);
        int statusCode = applyRepository.update_interviewStatus(id, resumeId, "completed");
        String statusUpdated = applyRepository.get_interviewStatus(id, resumeId);

         obj.put("id", id);
         obj.put("resumeId", resumeId);
         obj.put("evaluation", updated);
         obj.put("code", code);
         obj.put("statusCode", statusCode);
         obj.put("interviewStatus", statusUpdated);

        return obj;
    }
}
