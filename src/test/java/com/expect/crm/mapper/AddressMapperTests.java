package com.expect.crm.mapper;


import com.expect.crm.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(19);
        address.setPhone("18991395891");
        address.setName("Gwen");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUid(19);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list=addressMapper.findByUid(23);
        System.out.println(list);
    }

    @Test
    public void findByAid(){
        System.err.println(addressMapper.findByAid(14));
    }

    @Test
    public void updateNonDefault(){
        addressMapper.updateNonDefault(22);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(14,new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(13);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(22));
    }
}
