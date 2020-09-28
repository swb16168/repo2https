package com.swb.shiro.controller;

import com.swb.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 72774
 * @Description
 * @create 2020-09-27 13:55
 */

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("login")
    public Map<String,User> login(User user){
        Map<String,User>  userMap=  new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        subject.login(token);
        userMap.put("user",user);
        return userMap;
    }

    @GetMapping("test")
    public String test(){
        System.out.println("teddst");
        return "test";
    }



}
