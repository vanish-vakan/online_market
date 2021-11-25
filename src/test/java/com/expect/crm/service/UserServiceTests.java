package com.expect.crm.service;


import com.expect.crm.entity.User;
import com.expect.crm.service.ex.ServiceException;
import com.expect.crm.service.ex.UpdateException;
import com.expect.crm.service.ex.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
//启动单元测试类
@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserService userService;



    @Test
    public void insert(){
        try {
            User user =new User();
            user.setUsername("Spiderman");
            user.setPassword("Gwen");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            e.printStackTrace(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user=userService.login("Peter","loveGwen");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(7,"Gwen","beautyGwen");
    }


    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(1));
    }

    @Test
    public void changeInfo() {
        User user=new User();
        user.setPhone("18991495891");
        user.setModifiedTime(new Date());
        user.setEmail("843523836@qq.com");
        userService.changeInfo(1,"风马",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(22,"/upload/test.png");
    }


}
