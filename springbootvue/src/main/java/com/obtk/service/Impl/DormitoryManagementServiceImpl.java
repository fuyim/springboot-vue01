package com.obtk.service.Impl;

import com.obtk.bean.*;
import com.obtk.mapper.DormitoryManagementDao;
import com.obtk.service.DormitoryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DormitoryManagementServiceImpl implements DormitoryManagementService {

    @Autowired
    private DormitoryManagementDao dao;

    @Override
    public List<DormitoryArea> findByDormitoryArea(int id) {
        return dao.findByDormitoryArea(id);
    }

    @Override
    public List<PersonalInfo> findPersonal(Integer id) {
        return dao.findPersonal(id);
    }

    @Override
    @Transactional
    public Boolean insertIntoUpPass(Integer id, String text) {
        Boolean flag = false;
        try {
            dao.insertIntoUpPass(id,text);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Boolean deleteUpPass(Integer id) {
        Boolean flag = false;
        try {
            dao.deleteUpPass(id);
            flag =true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<UserInfo> findByPersonalId(Integer post, Integer id) {
        List<UserInfo> list = null;
        if (post == 1){
            list = dao.findByPersonalIdadministrator(id);
        }else if (post == 0){
            list = dao.findByPersonalIdUser(id);
        }else {
            return null;
        }
        return list;
    }

    @Override
    public ImageFile findByIdImage(Integer id) {
        return dao.findByIdImage(id);
    }

    @Override
    public List<UserInfo> findByDormitoryIDUser(Integer dormitoryID) {
        return dao.findByDormitoryIDUser(dormitoryID);
    }

    //    @Override
//    public List<User> findByPersonal(Integer id) {
//        return dao.findByPersonal(id);
//    }


    @Override
    public List<UserInfo> findByDormitoryAreaID(Integer dormitoryAreaID) {
        return dao.findByDormitoryAreaID(dormitoryAreaID);
    }

    @Override
    public List<UserInfo> findByDormitoryByID(Integer dormitoryAreaID) {
        return dao.findByDormitoryByID(dormitoryAreaID);
    }

    @Override
    public List<UserInfo> findSignInByDormitoryID(Integer dormitoryID) {
        return dao.findSignInByDormitoryID(dormitoryID);
    }

    @Override
    public Integer findScore() {
        return dao.findScore();
    }

    @Override
    public List<Equipment> findEquipmentState(Integer dormitoryAreaID) {
        return dao.findEquipmentState(dormitoryAreaID);
    }

    @Override
    @Transactional
    public void updateRecipientsState(Integer state) {
        dao.updateRecipientsState(state);
    }

    @Override
    public Recipients findRecipientsState() {
        return dao.findRecipientsState();
    }

    @Override
    public List<DormitoryRate> findDormitoryRate(Integer dormitoryAreaID) {
        return dao.findDormitoryRate(dormitoryAreaID);
    }

    @Override
    public Boolean updateDormitoryRate(Integer score ,Integer id) {
        Boolean flag = false;
        try {
            dao.updateDormitoryRate(score,id);
            flag =true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
