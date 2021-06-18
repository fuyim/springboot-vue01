package com.obtk.service;

import com.obtk.bean.*;

import java.util.List;

public interface DormitoryManagementService {
    List<DormitoryArea> findByDormitoryArea(int id);

    List<PersonalInfo> findPersonal(Integer id);

    Boolean insertIntoUpPass(Integer id, String text);

    Boolean deleteUpPass(Integer id);

    List<UserInfo> findByPersonalId(Integer post, Integer id);

    ImageFile findByIdImage(Integer id);

    List<UserInfo> findByDormitoryIDUser(Integer dormitoryID);

    List<UserInfo> findByDormitoryAreaID(Integer dormitoryAreaID);

    List<UserInfo> findByDormitoryByID(Integer dormitoryAreaID);

    List<UserInfo> findSignInByDormitoryID(Integer dormitoryID);

//    List<User> findByPersonal(Integer id);
}
