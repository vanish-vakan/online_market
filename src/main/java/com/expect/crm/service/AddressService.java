package com.expect.crm.service;


import com.expect.crm.entity.Address;

import java.util.List;

public interface AddressService {


    void addNewAddress(Address address,Integer uid);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid,Integer uid);
}
