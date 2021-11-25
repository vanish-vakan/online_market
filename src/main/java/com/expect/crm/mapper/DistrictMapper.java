package com.expect.crm.mapper;


import com.expect.crm.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {


     List<District> findByParent(String parent);

     String findNameByCode(String code);

}
