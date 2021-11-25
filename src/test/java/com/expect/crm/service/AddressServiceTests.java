package com.expect.crm.service;


import com.expect.crm.entity.Address;
import com.expect.crm.entity.User;
import com.expect.crm.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
//启动单元测试类
@SpringBootTest
public class AddressServiceTests {


    @Autowired
    private AddressService addressService;



    @Test
    public void addNewAddress(){
        Address address =new Address();
        address.setPhone("18991395891");
        address.setName("Peter");
        addressService.addNewAddress(address,20);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(13,22);
    }

}
