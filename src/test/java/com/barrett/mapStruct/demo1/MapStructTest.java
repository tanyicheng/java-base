package com.barrett.mapStruct.demo1;

import java.util.Date;

/**
 * 参考：https://juejin.cn/post/7140149801991012365
 * @author created by barrett in 2023/10/18 08:47
 */
public class MapStructTest {
    public static void main(String[] args) {
        testNormal();
    }

    public static void testNormal() {
        System.out.println("-----------testNormal-----start------");
        UserPo userPo = UserPo.builder()
                .id(1L)
                .gmtCreate(new Date())
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age(18L) 
                .build();
        System.out.println("1234" + userPo);
        UserEntity userEntity = IPersonMapper.INSTANCT.po2entity(userPo);
        System.out.println(userEntity);
        System.out.println("-----------testNormal-----ent------");
    }
}