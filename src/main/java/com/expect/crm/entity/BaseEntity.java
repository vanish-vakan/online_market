package com.expect.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;



@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {


    private Date createdTime;


    private Date modifiedTime;



}
