package com.expect.crm.service;


import com.expect.crm.entity.Order;

public interface OrderService {

    Order create(Integer aid, Integer[] cids, Integer uid);
}
