package com.ribbon2.ribbon2.controller;

import com.ribbon2.ribbon2.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("hello")
    public String hello(String name){
        //调用Service接口，并返回JSON数据
        return helloService.obtainOtherServiceData(name);
    }
}
