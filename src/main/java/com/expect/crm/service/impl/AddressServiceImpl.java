package com.expect.crm.service.impl;

import com.expect.crm.entity.Address;
import com.expect.crm.mapper.AddressMapper;
import com.expect.crm.service.AddressService;
import com.expect.crm.service.DistrictService;
import com.expect.crm.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Address address, Integer uid) {
        Integer count=addressMapper.countByUid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }



        String provinceName=districtService.getNameByCode(address.getProvinceCode()) ;
        String cityName=districtService.getNameByCode(address.getCityCode()) ;
        String areaName=districtService.getNameByCode(address.getAreaCode()) ;

        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDefault =count==0?1:0;
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer rows=addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        for (Address a:list) {
            a.setUid(null);
            a.setAid(null);
            a.setProvinceCode(null);
            a.setCityCode(null);
            a.setZip(null);
            a.setTel(null);
            a.setIsDefault(null);
            a.setPhone(null);
            a.setAreaCode(null);
            a.setCreatedTime(null);
            a.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid) {
        Address result=addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows=addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时发生异常");
        }
        rows=addressMapper.updateDefaultByAid(aid,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时发生异常");
        }


    }

}
