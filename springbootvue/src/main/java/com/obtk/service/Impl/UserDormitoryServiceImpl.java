package com.obtk.service.Impl;

import com.obtk.bean.CommentInfo;
import com.obtk.bean.Equipment;
import com.obtk.mapper.UserDormitoryDao;
import com.obtk.service.UserDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDormitoryServiceImpl implements UserDormitoryService {

    @Autowired
    private UserDormitoryDao dao;

    @Override
    @Transactional
    public Boolean UserSignIn(String sigInTime, Integer id, String leaveInfo) {
        Boolean flag = false;
        try {
            dao.UserSignIn(sigInTime,id,leaveInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    @Transactional
    public Boolean MaintenanceReport(int id, String reminderInfo,String reminderTime,Integer dormitoryID,Integer equipmentkey) {
        Boolean flag = false;
        try {
            dao.MaintenanceReport(id,reminderInfo,reminderTime,dormitoryID,equipmentkey);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Equipment> findReportBydormitoryID(Integer dormitoryID) {
        return dao.findReportBydormitoryID(dormitoryID);
    }

    @Override
    @Transactional
    public Boolean updateEquipmentState(Integer dormitoryID) {
        Boolean flag = false;
        try {
            dao.updateEquipmentState(dormitoryID);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    @Transactional
    public Boolean updateComment(Integer dormitoryID,String commentInfo,Integer equipmentkey) {
        Boolean flag = false;
        try {
            dao.updateComment(dormitoryID,commentInfo,equipmentkey);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Boolean markScore(Integer id,Integer score) {
        Boolean flag = false;
        try {
            dao.markScore(id,score);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<CommentInfo> findAllComment() {
        return dao.findAllComment();
    }
}
