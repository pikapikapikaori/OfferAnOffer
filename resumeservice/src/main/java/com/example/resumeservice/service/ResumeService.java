package com.example.resumeservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.resumeservice.dao.ResumeRepository;
import com.example.resumeservice.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Service
public class ResumeService {
    private ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) { this.resumeRepository = resumeRepository;}

    public JSONObject add_resume_name(String resumeName, String id, String resumeId){
        JSONObject obj = new JSONObject();

        int code = resumeRepository.update_resumeName(resumeName, id, Integer.parseInt(resumeId));

        obj.put("resumeName", resumeName);
        obj.put("id",id);

        return obj;
    }

    public JSONArray get_personal_center_data(String id){
        JSONArray arr = new JSONArray();

        ArrayList<Resume> res = resumeRepository.get_personal_center_data(id);

        if (res == null)
            return arr;

        for (int i = 0; i < res.size(); i ++) {
            Resume tmp = res.get(i);
            JSONObject obj = new JSONObject();

            obj.put("resumeId", tmp.getResumeId());
            obj.put("resumeName", tmp.getResumeName());
            obj.put("jobName", tmp.getJobName());

            arr.add(obj);
        }

        return arr;
    }

    public JSONArray get_hr_personal_center_data(){
        JSONArray arr = new JSONArray();
        ArrayList<Resume> res = resumeRepository.get_hr_personal_center_data();

        if (res == null)
            return arr;

        for (int i = 0; i < res.size(); i ++)
            for (int j = i + 1; j < res.size(); j ++) {
                if (Objects.equals(res.get(i).getId(), res.get(j).getId())) {
                    res.remove(res.get(j));
                }
            }

        for (int i = 0; i < res.size();i++){
            JSONObject obj = new JSONObject();
            obj.put("id",res.get(i).getId());
            obj.put("name",res.get(i).getName());
            arr.add(obj);
        }
        return arr;

    }

    public JSONObject add_resume(String id, String jobId, String jobName, String resumeName, String name){
        JSONObject obj = new JSONObject();

        //检测是否存在
        Resume targetResume = resumeRepository.get_resume(id, jobId);

        int resR;

        if(targetResume == null){
            resR = 1;

            Resume newResume = new Resume();
            newResume.setId(id);
            newResume.setJobId(jobId);
            newResume.setJobName(jobName);
            newResume.setResumeName(resumeName);
            newResume.setName(name);
            Resume res = resumeRepository.saveAndFlush(newResume);

            obj.put("id",res.getId());
            obj.put("jobId",res.getJobId());
            obj.put("jobName",res.getJobName());
            obj.put("resumeName",res.getResumeName());
            obj.put("name",res.getName());

            obj.put("code", resR);


            //obj.put("resumeContent",res.getResumeContent());
        }
        else {
            if (Objects.equals(targetResume.getId(), id) && Objects.equals(targetResume.getJobId(), jobId)) {
                resR = 0;

                obj.put("id","");
                obj.put("jobId","");
                obj.put("jobName","");
                obj.put("resumeName","");
                obj.put("name","");

                obj.put("code", resR);
            }
            else {
                resR = 1;

                Resume newResume = new Resume();
                newResume.setId(id);
                newResume.setJobId(jobId);
                newResume.setJobName(jobName);
                newResume.setResumeName(resumeName);
                newResume.setName(name);
                Resume res = resumeRepository.saveAndFlush(newResume);

                obj.put("id",res.getId());
                obj.put("jobId",res.getJobId());
                obj.put("jobName",res.getJobName());
                obj.put("resumeName",res.getResumeName());
                obj.put("name",res.getName());

                obj.put("code", resR);
            }
        }
        return obj;


    }
}
