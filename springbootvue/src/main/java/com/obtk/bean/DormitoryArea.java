package com.obtk.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DormitoryArea implements Serializable {
    private Integer id;
    private String dormitory_name;
    private Integer u_id;
    private Boolean state;
}
