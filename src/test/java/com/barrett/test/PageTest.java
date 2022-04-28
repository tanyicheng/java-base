package com.barrett.test;

import java.util.HashMap;
import java.util.List;

public class PageTest {

    public static void main(String[] args) {

        //新签核任务 0
        //新签核任务1-10（假设有3条）
        //第1页,10条，wft(0,10)=3;act(0,7)=7
        //第2页,10条，wft(10,10)=0;act(10,10)=7
        //第3页,10条，wft(20,10)=0;act(20,10)=7

        //新签核任务大于10（假设有18条）
        //第1页,10条，wft(0,10)=10;act 不查询;
        //第2页,10条，wft(10,10)=8;act(0,2)=2;(start!=0,start+size-wft总数;10+10-18=2)
        //第3页,10条，wft(20,10)=0;act(3,10)=10;(20+10-18=12);(12-10+1),(10)
        //第4页,10条，wft(30,10)=0;act(13,10)=10;(30+10-18=22);(22-20+1),(10)


        //分页查询起始,第一页
        int start = 10;
        int queryWft = 2;//第一页查询结果
        int wftAll = 2;//新版签核任务总数
        int size = 10;//每页查询数量
        //第2页开始
        if (start >= 10) {
            queryWft = wftAll - start;
        }

        if (queryWft < 0) {
            queryWft = 0;
        }
        System.out.println("start：" + start + "，size：" + size + "，queryWft：" + queryWft + "，wftAll：" + wftAll);
        test(start, size, queryWft, wftAll);
    }

    public static void test(int start, int size, int queryWft, int wftAll) {
        //查询act中起始位置和数量 limit actStart,actSize
        int actStart = 0;
        int actSize = 0;

        //新版签核数量>页面查询数量，不需要查询 act
        if (queryWft >= size) {
        } else {
            //新版签核数量不足页面查询数量，需要查询act补充
            //需要从act中补充的数量
            actSize = size - queryWft;
            actStart = start - wftAll ;

            if (actStart < 0) {
                actStart = 0;
            }
        }

        System.out.println("act 分页查询 limit " + actStart + "," + actSize);
    }
}
