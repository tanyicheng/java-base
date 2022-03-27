package com.barrett.http.controller;

import com.barrett.base.io.io1.FileUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 千锋教育uni-app接口
 * @Author created by barrett in 2022/3/20 09:51
 */
@RestController
@RequestMapping("/v2")
public class UniAppSNSController extends BaseCtrl{


    @RequestMapping("/hello")
    public String hello() {

        return "Hello Spring Boot!";
    }


    /**
     * 获取广告
     *
     * @Author created by barrett in 2022/3/19 11:21
     */
    @GetMapping("/advertisingspace/advertising")
    public String advertisingspace(@RequestParam("space") String space) {
        System.out.println(space);
        return readJsonFile("advertising.json");
    }

    /**
     * 获取动态信息
     * @Author created by barrett in 2022/3/19 16:48
     */
    @GetMapping("/feeds")
    public String feeds(@RequestParam(value = "space",required = false) String space) {
        return readJsonFile("feeds.json");
    }
    /**
     * 动态详情
     * @Author created by barrett in 2022/3/20 11:20
     */
    @GetMapping("/feeds/{id}")
    public String feedById(@PathVariable String id) {
        return readJsonFile("feed-info.json");
    }

    /**
     * 动态评论详情
     * @Author created by barrett in 2022/3/20 11:24
     */
    @GetMapping("/feeds/{id}/comments")
    public String feedCommentsById(@PathVariable String id) {
        return readJsonFile("feed-info.json");
    }

    /**
     * 获取资讯
     * @Author created by barrett in 2022/3/19 20:33
     */
    @GetMapping("/news")
    public String news(@RequestParam(value = "space",required = false) String space) {
        return readJsonFile("news.json");
    }

    @GetMapping("/news/{id}")
    public String newsById(@PathVariable String id) {
        return readJsonFile("news-info.json");
    }

    @GetMapping("/files")
    public String files(@RequestParam(value = "space",required = false) String space) {
        return "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp01%2F1ZZQ20QJS6-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650252645&t=8cefa5ffc0e4955845c320660386c058";
    }
    @GetMapping("/files/{name}")
    public String files2(@PathVariable String name) {
        System.out.println(name);
        return "http://120.27.202.176:98/wfile/prod/202112/1640758692000.dceg0yy80dv.jpg";
    }


    public static void main(String[] args) {
        String s = FileUtils.imgToBase64ByInputStream("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp01%2F1ZZQ20QJS6-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650252645&t=8cefa5ffc0e4955845c320660386c058");
//        System.out.println(s);

    }
}