package com.oaoffer.processservice.service;
import com.alibaba.fastjson.JSONObject;
import com.oaoffer.processservice.dao.ApplyRepository;
import com.oaoffer.processservice.dao.UserRepository;
import com.oaoffer.processservice.entity.Apply;
import com.oaoffer.processservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SystemUtilService {
    private UserRepository userRepository;
    private ApplyRepository applyRepository;

    @Autowired
    public SystemUtilService(UserRepository userRepository, ApplyRepository applyRepository) {
        this.userRepository = userRepository;
        this.applyRepository = applyRepository;
    }

    public JSONObject add_user(String id, String name, String identity) {
        // int resU = userRepository.insert_user(id, name, identity);

        JSONObject obj = new JSONObject();

        User targetUser = userRepository.get_user(id);

        int resU;

        if(targetUser == null) {
            resU = 1;

            User newUser = new User();
            newUser.setId(id);
            newUser.setIdentity(identity);
            newUser.setName(name);
            User res = userRepository.saveAndFlush(newUser);

            obj.put("code", resU);
            obj.put("id", res.getId());
            obj.put("name", res.getName());
            obj.put("identity", res.getIdentity());
        }
        else {
            if(Objects.equals(targetUser.getId(), id)) {
                resU = 0;

                obj.put("code", resU);
                obj.put("id", "");
                obj.put("name", "");
                obj.put("identity", "");
            }
            else {
                resU = 1;

                User newUser = new User();
                newUser.setId(id);
                newUser.setIdentity(identity);
                newUser.setName(name);
                User res = userRepository.saveAndFlush(newUser);

                obj.put("code", resU);
                obj.put("id", res.getId());
                obj.put("name", res.getName());
                obj.put("identity", res.getIdentity());
            }
        }

        return obj;
    }

    public JSONObject add_apply(String id, String resumeId) {
        /*
        int resA = applyRepository.insert_apply(resumeId, id, "resume",
                "waiting", "waiting", "",
                "waiting");
         */

        JSONObject obj = new JSONObject();

        Apply targetApply = applyRepository.get_apply(id, resumeId);

        int resA;

        if(targetApply == null) {
            resA = 1;

            Apply newApply = new Apply();
            newApply.setResumeId(resumeId);
            newApply.setId(id);
            newApply.setStatus("resume");
            newApply.setResumeStatus("waiting");
            newApply.setInterviewStatus("waiting");
            newApply.setOfferStatus("waiting");
            newApply.setEvaluation("");
            Apply res = applyRepository.saveAndFlush(newApply);

            obj.put("code", resA);
            obj.put("id", res.getId());
            obj.put("resumeId", res.getResumeId());
            obj.put("status", res.getStatus());
            obj.put("resumeStatus", res.getResumeStatus());
            obj.put("interviewStatus", res.getInterviewStatus());
            obj.put("offerStatus", res.getOfferStatus());
            obj.put("evaluation", res.getEvaluation());
        }
        else {
            if(Objects.equals(targetApply.getResumeId(), resumeId)) {
                resA = 0;

                obj.put("code", resA);
                obj.put("id", "");
                obj.put("resumeId", "");
                obj.put("status", "");
                obj.put("resumeStatus", "");
                obj.put("interviewStatus", "");
                obj.put("offerStatus", "");
                obj.put("evaluation", "");
            }
            else {
                resA = 1;

                Apply newApply = new Apply();
                newApply.setResumeId(resumeId);
                newApply.setId(id);
                newApply.setStatus("resume");
                newApply.setResumeStatus("waiting");
                newApply.setInterviewStatus("waiting");
                newApply.setOfferStatus("waiting");
                newApply.setEvaluation("");
                Apply res = applyRepository.saveAndFlush(newApply);

                obj.put("code", resA);
                obj.put("id", res.getId());
                obj.put("resumeId", res.getResumeId());
                obj.put("status", res.getStatus());
                obj.put("resumeStatus", res.getResumeStatus());
                obj.put("interviewStatus", res.getInterviewStatus());
                obj.put("offerStatus", res.getOfferStatus());
                obj.put("evaluation", res.getEvaluation());
            }
        }


        return obj;
    }
}
