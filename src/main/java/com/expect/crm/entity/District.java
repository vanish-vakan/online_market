package com.expect.crm.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class District extends BaseEntity{

    private Integer id;

    private String parent;

    private String code;

    private String name;


}
