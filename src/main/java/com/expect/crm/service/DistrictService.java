package com.expect.crm.service;


import com.expect.crm.entity.District;

import java.util.List;

public interface DistrictService {

    List<District> getByParent(String parent);

    String getNameByCode(String code);

}

