package com.barrett.mapStruct.demo1;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private Long id;
    private Date gmtCreate;
    private Date createTime;
    private Long buyerId;
    private Long age;
    private String userNick1;
    private String userVerified;
    private Attributes attributes;
}
