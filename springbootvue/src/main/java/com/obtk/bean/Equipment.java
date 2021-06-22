package com.obtk.bean;

import lombok.Data;

@Data
public class Equipment {
    private Integer id;
    private String reminderTime;
    private Integer u_id;
    private String reminderInfo;
    private Integer dormitory_id;
    private Boolean equipmentState;
    private String commentInfo;
    private Dormitory dormitory;
}
