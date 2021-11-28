package com.expect.crm.controller;

import com.expect.crm.entity.Order;
import com.expect.crm.service.OrderService;
import com.expect.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("orders")
@RestController
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid);
        // 返回成功与数据
        return new JsonResult<Order>(Ok, data);
    }
}
