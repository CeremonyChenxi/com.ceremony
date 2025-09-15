package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);
    @Select("select * from user where id=#{id}")
    User getByid(Long  id);
    Integer insert(User user);

}
