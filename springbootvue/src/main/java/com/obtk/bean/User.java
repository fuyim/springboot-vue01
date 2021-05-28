package com.obtk.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String telephone;
    private String email;
    private String IDCard;
    private String QQ;
    private Integer post;
    private String crateData;
    private String adminName;
    private String nation;
}
