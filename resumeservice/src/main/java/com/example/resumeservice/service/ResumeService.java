package com.example.resumeservice.service;

import com.alibaba.fastjson.JSONObject;
import com.example.resumeservice.dao.ResumeRepository;
import com.example.resumeservice.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    private ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) { this.resumeRepository = resumeRepository;}

    public JSONObject update_resumeContent(String id, String resumeId, byte[] resumeContent){
        JSONObject obj = new JSONObject();

        int code = resumeRepository.update_resumeContent(id, resumeId, resumeContent);

        byte[] updated = resumeRepository.get_resumeContent(id, resumeId);

        obj.put("id", id);
        obj.put("resumeId", resumeId);
        obj.put("resumeContent", updated);
        obj.put("code", code);

        return obj;
    }

    public JSONObject add_resume(String id, byte[] resumeContent, String jobId){
        JSONObject obj = new JSONObject();

        //检测是否存在
        Resume targetResume = resumeRepository.get_resume(id, jobId);

        int resR;

        if(targetResume == null){
            resR = 1;

            Resume newResume = new Resume();
            newResume.setId(id);
            newResume.setJobId(jobId);
            newResume.setResumeContent(resumeContent);
            Resume res = resumeRepository.saveAndFlush(newResume);

            obj.put("code", resR);
            obj.put("resumeId", res.getResumeId());
            obj.put("id",res.getId());
            obj.put("jobId",res.getJobId());
            //obj.put("resumeContent",res.getResumeContent());
        }
        return obj;



    }
}
