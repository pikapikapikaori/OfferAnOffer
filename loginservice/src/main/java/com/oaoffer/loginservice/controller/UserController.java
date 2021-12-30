package com.oaoffer.loginservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.oaoffer.loginservice.Constant.Constant;
import com.oaoffer.loginservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth", produces = { "application/json;charset=UTF-8" })
public class UserController {
    private UserService userService;
    private RestTemplate restTemplate;

    @Autowired
    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register")
    public JSONObject register(String email, String password) {
        System.out.println(email);

        JSONObject obj = userService.register(email, password);

        register_add_user(obj.getString("id"), obj.getString("email"), obj.getString("identity"));

        return obj;
    }

    @GetMapping("/login")
    public JSONObject login(String email, String password) {
        return userService.login(email, password);
    }

    @PostMapping("register_add_user")
    public JSONObject register_add_user(String id, String username, String identity) {
        String url = "/system/add_user";

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("id", id);
        requestEntity.add("name", username);
        requestEntity.add("identity", identity);

        return restTemplate.postForObject(Constant.baseProcessUrl + url, requestEntity, JSONObject.class);
    }
}
