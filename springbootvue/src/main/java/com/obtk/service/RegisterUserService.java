package com.obtk.service;

import com.obtk.bean.DormitoryArea;
import com.obtk.bean.User;
import com.obtk.bean.administratorCode;

import java.util.List;

public interface RegisterUserService {
    List<administratorCode> findAllCode();

    Boolean register(User user);

    void insertImage(Integer id);

    User findUserID(String username, String password);

    List<DormitoryArea> findAllDormitoryArea();

    Boolean updateDormitoryArea(Integer id,Integer optionsId);
}
