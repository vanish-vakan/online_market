package com.expect.crm.controller;


import com.expect.crm.service.ex.*;
import com.expect.crm.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class BaseController implements Serializable {

    public static final int Ok=200;


//    当项目中产生了异常，会被拦截到此方法中，请求处理方法，返回值会传给前端。
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(2000);
            result.setMessage("用户名已经被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户名的密码错误异常");
        }else if(e instanceof AccessDeniedException){
            result.setState(4004);
            result.setMessage("收货地址非常访问的异常");
        }else if(e instanceof AddressNotFoundException){
            result.setState(4005);
            result.setMessage("收货地址不存在的异常");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品不存在的异常");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车订单商品不存在");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生位置的异常");
        }else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        }else if(e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除数据产生的异常");
        }else if(e instanceof FileEmptyException){
            result.setState(6001);
            result.setMessage("文件为空的异常");
        }else if(e instanceof FileStateException){
            result.setState(6002);
            result.setMessage("文件状态的异常");
        }else if(e instanceof FileUploadIOException){
            result.setState(6003);
            result.setMessage("文件上传IO异常");
        }else if(e instanceof FileSizeException){
            result.setState(6004);
            result.setMessage("文件大小的异常");
        }else if(e instanceof FileTypeException){
            result.setState(6005);
            result.setMessage("文件类型的异常");
        }else if(e instanceof AddressCountLimitException){
            result.setState(5002);
            result.setMessage("地址超出异常限制");
        }



        return result;
    }

    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }



}
