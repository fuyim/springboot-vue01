package com.obtk.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalInfo implements Serializable {
    private Integer id;
    private String username;
    private String telephone;
    private String nation;
    private String dormitoryName;
}
