package com.expect.crm.mapper;

import com.expect.crm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


@Mapper
public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String name);

    Integer updatePasswordByUid(Integer uid, String password, Date modifiedTime);

    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(@Param("uid") Integer uid,@Param("avatar") String avatar,@Param("modifiedTime") Date modifiedTime);

}
