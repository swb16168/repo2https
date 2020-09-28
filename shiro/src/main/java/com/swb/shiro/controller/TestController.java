package com.swb.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 72774
 * @Description
 * @create 2020-09-27 14:24
 */
@RestController
@RequestMapping("test")
public class TestController {


    @RequestMapping("test")
    public String test(){
        return "test/test";
    }

}
