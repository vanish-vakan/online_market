package com.expect.crm.service.impl;

import com.expect.crm.entity.District;
import com.expect.crm.mapper.DistrictMapper;
import com.expect.crm.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DistrictServiceImpl implements DistrictService {


    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {

        List<District> list=districtMapper.findByParent(parent);

        for (District d:list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;


    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
