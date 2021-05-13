package com.obtk.service;

import com.obtk.bean.ImageFile;
import com.obtk.bean.User;

import java.util.List;


public interface UserService {

    //登录操作
    public User login(String telephoneEmail , String password);

    Boolean updateImage(Integer id, String imageUrl, String fileName);

    ImageFile getByIdImage(Integer id);

    List<User> findAll(Integer id);
}
