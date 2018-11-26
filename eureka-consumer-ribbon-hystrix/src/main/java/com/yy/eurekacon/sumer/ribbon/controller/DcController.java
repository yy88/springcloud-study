package com.yy.eurekacon.sumer.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yuanyuan.jing
 * @date: 2018/11/2 14:39
 * @description:
 */
@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 服务降级，使用HystrixCommand指定服务降级执行方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/consumer")
    public String dc() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    /**
     * 服务降级执行方法
     * @return
     */
    public String fallback() {
        return "fallback";
    }
}
