package com.feign.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SERVICE-CLIENT",fallback = ErrorHandler.class)
public interface HelloService {

    /**
     * 从SERVICE-CLIENT服务器的/hi接口获取JSON数据
     * @param name
     * @return
     */
    @RequestMapping("/hi")
    String obtainOtherServerJsonData(@RequestParam(value = "name") String name);

}
