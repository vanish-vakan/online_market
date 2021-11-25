package com.expect.crm.mapper;


import com.expect.crm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
//启动单元测试类
@SpringBootTest
public class UserMapperTests {


    @Autowired
    private UserMapper userMapper;



    @Test
    public void insert(){
        User user =new User();
        user.setUsername("ABC");
        user.setPassword("67890");
        Integer rows=userMapper.insert(user);
        System.out.println(rows);
    }


    @Test
    public void findByUsername(){
        User user=userMapper.findByUsername("Ted");
        System.out.println(user);
    }


    @Test
    public void updatePasswordByUid(){

        userMapper.updatePasswordByUid(1,"loveGwenforever",new Date());


    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(1));
    }


    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(1);
        user.setPhone("18991395891");
        user.setEmail("843523836@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }


    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(22,"/upload/avatar.png",new Date());
    }



}
