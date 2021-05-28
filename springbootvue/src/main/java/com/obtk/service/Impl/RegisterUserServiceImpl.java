package com.obtk.service.Impl;

import com.obtk.bean.DormitoryArea;
import com.obtk.bean.User;
import com.obtk.bean.administratorCode;
import com.obtk.mapper.RegisterUserDao;
import com.obtk.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserDao dao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public List<administratorCode> findAllCode() {
        List<administratorCode> list = null;
        try {
            list =  dao.findAllCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    @Transactional
    public Boolean register(User user) {
        Boolean flag = false;
        try {
            dao.register(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    @Transactional
    public void insertImage(Integer id) {
        try {
            dao.insertImage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserID(String username, String password) {
        User user = dao.finUserID(username,password);
        return user;
    }

    @Override
    public List<DormitoryArea> findAllDormitoryArea() {
        return dao.findAllDormitoryArea();
    }

    @Override
    @Transactional
    public Boolean updateDormitoryArea(Integer id,Integer optionsId) {
        return dao.updateDormitoryArea(id,optionsId);
    }
}
