package com.expect.crm.controller;

import com.expect.crm.entity.Address;
import com.expect.crm.service.AddressService;
import com.expect.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("addresses")
@Controller
public class AddressController extends BaseController{

    @Autowired
    private AddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addAddress(Address address,HttpSession session){
        Integer uid=getuidFromSession(session);
        addressService.addNewAddress(address,uid);
        return new JsonResult<>(Ok);

    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(Ok,data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(
                aid,
                getuidFromSession(session)
        );
        return new JsonResult<>(Ok);
    }



}
