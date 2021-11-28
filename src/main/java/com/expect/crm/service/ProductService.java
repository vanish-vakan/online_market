package com.expect.crm.service;

import com.expect.crm.entity.Product;

import java.util.List;

public interface ProductService {


    List<Product> findHotList();


    Product findById(Integer id);
}
