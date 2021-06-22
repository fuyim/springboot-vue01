package com.obtk.mapper;

import com.obtk.bean.CommentInfo;
import com.obtk.bean.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDormitoryDao {
    void UserSignIn(@Param("sigInTime") String sigInTime, @Param("id") Integer id,@Param("leaveInfo") String leaveInfo);

    void MaintenanceReport(@Param("id") int id, @Param("reminderInfo") String reminderInfo,@Param("reminderTime") String reminderTime,@Param("dormitoryID") Integer dormitoryID,@Param("equipmentkey") Integer equipmentkey);

    List<Equipment> findReportBydormitoryID(Integer dormitoryID);

    void updateEquipmentState(Integer dormitoryID);

    void markScore(@Param("id") Integer id,@Param("score") Integer score);

    void updateComment(@Param("dormitoryID") Integer dormitoryID,@Param("commentInfo") String commentInfo,@Param("equipmentkey") Integer equipmentkey);

    List<CommentInfo> findAllComment();
}
