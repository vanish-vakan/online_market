package com.expect.crm.service;


import com.expect.crm.entity.Address;
import com.expect.crm.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
//启动单元测试类
@SpringBootTest
public class DistrictServiceTests {


    @Autowired
    private  DistrictService districtService;



    @Test
    public void findByParent(){
        List<District> list =districtService.getByParent("86");

        for (District d:list) {
            System.out.println(d);
        }

    }

}
