package com.obtk.service.Impl;

import com.obtk.mapper.UserDormitoryDao;
import com.obtk.service.UserDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
