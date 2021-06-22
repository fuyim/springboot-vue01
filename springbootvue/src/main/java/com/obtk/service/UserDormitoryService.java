package com.obtk.service;

import com.obtk.bean.CommentInfo;
import com.obtk.bean.Equipment;

import java.util.List;

public interface UserDormitoryService {
    Boolean UserSignIn(String sigInTime, Integer id, String leaveInfo);

    Boolean MaintenanceReport(int id, String reminderInfo,String reminderTime,Integer dormitoryID,Integer equipmentkey);

    List<Equipment> findReportBydormitoryID(Integer dormitoryID);

    Boolean updateEquipmentState(Integer dormitoryID);

    Boolean markScore(Integer id,Integer score);

    Boolean updateComment(Integer dormitoryID,String commentInfo,Integer equipmentkey);

    List<CommentInfo> findAllComment();
}
