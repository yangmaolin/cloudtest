package com.feign.feign.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler implements HelloService{
    @Value("${server.port}")
    private String port;

    @Override
    public String obtainOtherServerJsonData(String name) {
        return "sorry " + name + " , " + port + " server internal error feign";
    }
}
