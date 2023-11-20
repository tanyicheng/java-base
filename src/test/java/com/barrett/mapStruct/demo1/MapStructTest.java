package com.barrett.mapStruct.demo1;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 参考：https://juejin.cn/post/7140149801991012365
 *
 * @author created by barrett in 2023/10/18 08:47
 */
public class MapStructTest {

    @Test
    public void testNormal() {
        System.out.println("-----------testNormal-----start------");
        String attributes = "{\"id\":2,\"name\":\"测试123\"}";
        UserPo userPo = UserPo.builder()
                .id(1L)
                .gmtCreate(new Date())
                .createTime("2023-10-01")
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age("18")
                .attributes(attributes)
                .build();
        System.out.println("1234" + userPo);
        UserEntity userEntity = IPersonMapper.INSTANCT.po2entity(userPo);
        System.out.println(userEntity);
        System.out.println("-----------testNormal-----ent------");

    }


    /**
     * 性能测试对比
     * @author created by barrett in 2023/11/13 08:16
     */
    @Test
    public void testTime() {
        System.out.println("-----------testTime-----start------");
        int times = 50000000;
        final long springStartTime = System.currentTimeMillis();
//        times = 50000000 实测 45秒
        for (int i = 0; i < times; i++) {
            UserPo userPo = UserPo.builder()
                    .id(1L)
                    .gmtCreate(new Date())
                    .buyerId(666L)
                    .userNick("测试123")
                    .userVerified("ok")
                    .build();
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userPo, userEntity);
        }

        final long springEndTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            UserPo userPo = UserPo.builder()
                    .id(1L)
                    .gmtCreate(new Date())
                    .buyerId(666L)
                    .userNick("测试123")
                    .userVerified("ok")
                    .build();
            UserEntity userEntity = IPersonMapper.INSTANCT.po2entity(userPo);
        }

        final long mapstructEndTime = System.currentTimeMillis();
        System.out.println("BeanUtils use time=" + (springEndTime - springStartTime) / 1000 + "秒" +
                "; Mapstruct use time=" + (mapstructEndTime - springEndTime) / 1000 + "秒");
        System.out.println("-----------testTime-----end------");
    }

}
