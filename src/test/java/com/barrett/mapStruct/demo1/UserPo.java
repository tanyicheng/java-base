package com.barrett.mapStruct.demo1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
    private Long id;
    private Date gmtCreate;
    private String createTime;
    private Long buyerId;
    private String age;
    private String userNick;
    private String userVerified;
    private String attributes;
}
