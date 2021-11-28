package com.expect.crm.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart extends BaseEntity implements Serializable {

    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;

}
