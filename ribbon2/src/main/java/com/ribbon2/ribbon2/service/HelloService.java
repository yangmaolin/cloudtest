package com.ribbon2.ribbon2.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    /**
     * 将IOC容器中的负载均衡RestTemplate工具注入进来
     */
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorEx")//注意函数的参数列表需保持一致
    public String obtainOtherServiceData(String name) {
        //尝试调用其它微服务接口，访问的是SERVICE-CLIENT这个服务器的/hi 接口
        return restTemplate.getForObject("http://SERVICE-CLIENT/hi?name=" + name, String.class);
    }

    public String errorEx(String name) {
        return "sorry " + name + " , server internal error";
    }
}
