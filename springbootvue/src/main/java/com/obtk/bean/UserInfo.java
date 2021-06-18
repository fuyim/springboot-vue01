package com.obtk.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private Integer id ;
    private String username;
    private String sex;
    private String telephone;
    private String nation;
    private String email;
    private String dormitoryAreaName;
    private String dormitoryName;
    private Integer dormitoryID;
    private Integer dormitoryAreaID;
    private SignIn signIn;
}
