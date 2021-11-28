package com.expect.crm.service;

import com.expect.crm.entity.Order;
import com.expect.crm.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//启动单元测试类
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 21;
            Integer[] cids = {4, 5, 6,7};
            Integer uid = 31;
            Order order = orderService.create(aid, cids, uid);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
