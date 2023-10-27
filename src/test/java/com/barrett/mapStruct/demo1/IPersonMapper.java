package com.barrett.mapStruct.demo1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPersonMapper {
    IPersonMapper INSTANCT = Mappers.getMapper(IPersonMapper.class);

    /*
    String转日期
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd")
    String转数字
    @Mapping(target = "age", source = "age", numberFormat = "#0.00")
    忽略某个字端
    @Mapping(target = "id", ignore = true)
    给默认值
    @Mapping(target = "userVerified", defaultValue = "defaultValue-2")
     */
    @Mapping(target = "userNick1", source = "userNick")
    UserEntity po2entity(UserPo userPo);
}
