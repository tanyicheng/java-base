package com.barrett.http.controller;

import com.barrett.base.io.io1.FileUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 千锋教育uni-app接口
 * @Author created by barrett in 2022/3/20 09:51
 */
@RestController
@RequestMapping("/jk")
public class JkmdController extends BaseCtrl{


    @RequestMapping("/hello")
    public String hello() {

        return "Hello Spring Boot!";
    }


    @RequestMapping("/title")
    public String hello2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("title");
        return readJsonFile("title.json");
    }

    @RequestMapping("/subject")
    public String subject() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("subject");
        return readJsonFile("subject.json");
    }


}