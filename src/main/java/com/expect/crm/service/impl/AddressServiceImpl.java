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

    @Override
    public void delete(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows=addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除数据示产生异常");
        }

        Integer count=addressMapper.countByUid(uid);
        if(count==0){
            return;
        }

        if(result.getIsDefault()==1){
            return;
        }

        Address address=addressMapper.findLastModified(uid);
        rows =addressMapper.updateDefaultByAid(address.getAid(),new Date());

        if(rows!=1){
            throw new UpdateException("更新数据时产生的异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedTime(null);
        address.setModifiedTime(null);
        return address;
    }
}
