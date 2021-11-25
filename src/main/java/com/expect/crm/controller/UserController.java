package com.expect.crm.controller;


import com.expect.crm.entity.User;
import com.expect.crm.service.UserService;
import com.expect.crm.service.ex.*;
import com.expect.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequestMapping("users")
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;


//后端接受前端数据
//    1.设置成pojo类型来接受前端的数据，springboot会将前端的url地址中的参数名和pojo中的属性名进行比较


    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(Ok);
    }

//    后端接受前端数据
//    2.设置成非pojo类型来接受前端的数据，springboot会将请求的参数名和方法的参数名直接进行比较，名称相同就注入


    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data=userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<>(Ok, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid =getuidFromSession(session);
        userService.changePassword(uid,oldPassword,newPassword);
        return new JsonResult<>(Ok);
    }




//    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user){
//        JsonResult<Void> result=new JsonResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        } catch (InsertException e){
//            result.setState(5000);
//            result.setMessage("注册时出现异常");
//        }
//        return result;
//    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data=userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(Ok,data);
    }


    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(Ok);
    }

    public static final int AVATAR_MAX_SIZE=10*1024*1024;

    public static final List<String> AVATAR_TYPE=new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");

    }


    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw  new FileSizeException("文件大小超出限制");
        }
        String contentType=file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        String parent=session.getServletContext().getRealPath("upload");
        File dir=new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String originalFilename=file.getOriginalFilename();
        System.out.println("OriginalFilename="+originalFilename);
        int index=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(index);
        String filename= UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest=new File(dir,filename);
        try {
            file.transferTo(dest);
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        Integer uid=getuidFromSession(session);
        String avatar="/upload/"+filename;
        userService.changeAvatar(uid,avatar);
        return new JsonResult<>(Ok,avatar);

    }


}
