package com.expect.crm.service;

import com.expect.crm.entity.User;

public interface UserService {

        void reg(User user);

        User login(String username,String password);

        void changePassword(Integer uid,String oldPassword,String newPassword);

        User getByUid(Integer uid);

        void changeInfo(Integer uid,String username,User user);

        void changeAvatar(Integer uid,String avatar);
}
