package com.expect.crm.service;

import com.expect.crm.vo.CartVO;

import java.util.List;

public interface CartService {

    void addToCart(Integer uid, Integer pid, Integer amount);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCids(Integer uid, Integer[] cids);
}
