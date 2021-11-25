package com.expect.crm.controller;


import com.expect.crm.entity.District;
import com.expect.crm.service.DistrictService;
import com.expect.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private DistrictService districtService;


    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> list=districtService.getByParent(parent);
        return new JsonResult<>(Ok,list);
    }

}
