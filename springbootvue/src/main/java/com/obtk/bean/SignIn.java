package com.obtk.bean;

import lombok.Data;

@Data
public class SignIn {
    private Integer id;
    private Integer u_id;
    private String signInTime;
    private String leaveInfo;
    private Boolean siginState;
//    private Integer states;
}
