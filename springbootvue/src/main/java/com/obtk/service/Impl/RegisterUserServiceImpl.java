package com.obtk.service.Impl;

import com.obtk.bean.*;
import com.obtk.mapper.RegisterUserDao;
import com.obtk.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserDao dao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public List<administratorCode> findAllCode() {
        List<administratorCode> list = null;
        try {
            list = dao.findAllCode();
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
        User user = dao.finUserID(username, password);
        return user;
    }

    @Override
    public List<DormitoryArea> findAllDormitoryArea() {
        return dao.findAllDormitoryArea();
    }

    @Override
    @Transactional
    public Boolean updateDormitoryArea(Integer id, Integer optionsId) {
        return dao.updateDormitoryArea(id, optionsId);
    }

    @Override
    public List<Dormitory> findAllDormitory() {
        List<Dormitory> list = null;
        try {
            list = dao.findAllDormitory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    @Transactional
    public Boolean insertStudentDormitory(Integer id, Integer optionsId) {
        Boolean flag = false;
        try {
            dao.insertStudentDormitory(id,optionsId);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
