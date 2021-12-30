package com.oaoffer.loginservice.service;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.loginservice.dao.UserRepository;
import com.oaoffer.loginservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JSONObject register(String email, String password) {
        JSONObject obj = new JSONObject();

        User targetUser = userRepository.get_user(email, password);

        int resU;

        if(targetUser == null) {
            resU = 1;

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setIdentity("interviewee");

            User res = userRepository.saveAndFlush(newUser);

            obj.put("code", resU);
            obj.put("id", Integer.toString(res.getId()));
            obj.put("email", res.getEmail());
            obj.put("password", res.getPassword());
            obj.put("identity", res.getIdentity());
        }
        else {
            if(Objects.equals(targetUser.getEmail(), email)){
                resU = 0; //注册失败
                obj.put("code", resU);
                obj.put("id", "");
                obj.put("email", "");
                obj.put("password", "");
                obj.put("identity", "");

            }
            else {
                resU = 1;

                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setIdentity("interviewee");

                User res = userRepository.saveAndFlush(newUser);

                obj.put("code", resU);
                obj.put("id", Integer.toString(res.getId()));
                obj.put("email", res.getEmail());
                obj.put("password", res.getPassword());
                obj.put("identity", res.getIdentity());

            }
        }

        return obj;
    }

    public JSONObject login(String email, String password) {
        JSONObject obj = new JSONObject();

        User targetUser = userRepository.get_user(email, password);

        int resU;

        if(targetUser == null) {
            resU = 0;
            obj.put("code", resU);
            obj.put("id", "");
            obj.put("email", "");
            obj.put("password", "");
            obj.put("identity", "");
        }
        else {
            if(Objects.equals(targetUser.getEmail(), email) && Objects.equals(targetUser.getPassword(), password)) {
                resU = 1; // 成功登陆码
                obj.put("code", resU);
                obj.put("id", Integer.toString(targetUser.getId()));
                obj.put("email", targetUser.getEmail());
                obj.put("password", targetUser.getPassword());
                obj.put("identity", targetUser.getIdentity());

            }
            else {
                resU = 0;
                obj.put("code", resU);
                obj.put("id", "");
                obj.put("email", "");
                obj.put("password", "");
                obj.put("identity", "");

            }
        }

        return obj;
    }
}
