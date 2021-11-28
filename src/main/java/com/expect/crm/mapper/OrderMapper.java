package com.expect.crm.mapper;


import com.expect.crm.entity.Order;
import com.expect.crm.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}
