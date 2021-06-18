package com.obtk.service;

import com.obtk.bean.*;

import java.util.List;


public interface UserService {

    //登录操作
    public User login(String telephoneEmail , String password);

    Boolean updateImage(Integer id, String imageUrl, String fileName);

    ImageFile getByIdImage(Integer id);

    List<User> findAll(Integer id,Integer personalId);

    Boolean updateUser(User user,Integer id);

    Boolean updatePass(Integer id, String newPass);

    List<Mz> findAllMz();

    List<UserInfo> findByPostUser(int id);

    List<User> findByTelephoneUser(String telephone);

    List<UpPass> findByIdCode(Integer id);

    Boolean UpPssByCode(Integer id, String telephone,String newPass);
}
