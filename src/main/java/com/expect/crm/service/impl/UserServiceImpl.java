package com.expect.crm.service.impl;

import com.expect.crm.entity.User;
import com.expect.crm.mapper.UserMapper;
import com.expect.crm.service.UserService;
import com.expect.crm.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result=userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
//        加密
        String oldPassword =user.getPassword();
        String salt=UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);

        String md5Password=getMD5Password(oldPassword,salt);
        user.setPassword(md5Password);


//        补全
        user.setIsDelete(0);
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
//        注册
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在注册过程中产生了位置的异常");
        }

    }

    @Override
    public User login(String username, String password) {
        User result =userMapper.findByUsername(username);

        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }

        String oldPassword=result.getPassword();
        String salt=result.getSalt();
        String newMD5Password=getMD5Password(password,salt);

        if(!newMD5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }

        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }


    @Override
    public void changePassword(Integer uid,String oldPassword, String newPassword) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        String odlMD5Password=getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(odlMD5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        String newMD5Password=getMD5Password(newPassword,result.getSalt());

        Integer rows=userMapper.updatePasswordByUid(uid,newMD5Password,new Date());

        if(rows!=1){
            throw new UpdateException("更新时产生未知异常");
        }


    }

    @Override
    public User getByUid(Integer uid) {
        User user=userMapper.findByUid(uid);
        if(user==null||user.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user1=new User();
        user1.setUsername(user1.getUsername());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result =userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedTime(new Date());

        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时发生异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar) {
        User result =userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows=userMapper.updateAvatarByUid(uid,avatar,new Date());
        if(rows!=1){
            throw new UpdateException("更新时发生异常");
        }

    }


    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
