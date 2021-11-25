package com.expect.crm.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address extends BaseEntity{
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode ;
    private String cityName ;
    private String cityCode ;
    private String areaName ;
    private String areaCode ;
    private String zip ;
    private String address ;
    private String phone ;
    private String tel ;
    private String tag ;
    private Integer isDefault ;
}
