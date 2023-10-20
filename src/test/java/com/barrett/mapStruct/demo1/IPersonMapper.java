package com.barrett.mapStruct.demo1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPersonMapper {
    IPersonMapper INSTANCT = Mappers.getMapper(IPersonMapper.class);

    @Mapping(target = "userNick1", source = "userNick")
    UserEntity po2entity(UserPo userPo);
}
