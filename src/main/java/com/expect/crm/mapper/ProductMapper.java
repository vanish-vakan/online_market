package com.expect.crm.mapper;

import com.expect.crm.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper {


    List<Product> findHotList();

    Product findById(Integer id);
}
