package com.obtk.mapper;

import com.obtk.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UserDao {

    public User login(@Param("telephoneEmail") String telephoneEmail,@Param("password") String password);

    Boolean updateImage(@Param("id") Integer id, @Param("imageUrl") String imageUrl, @Param("fileName") String fileName);

    ImageFile getByIdImage(Integer id);

    List<User> findAll(@Param("id") Integer id,@Param("personalId") Integer personalId);

    void updateUser(@Param("user") User user,@Param("id") Integer id);

    void updatePass(@Param("id") Integer id, @Param("newPass") String newPass);

    List<Mz> findAllMz();

    List<UserInfo> findByPostUser(int id);

    List<User> findByTelephoneUser(String telephone);

    List<UpPass> findByIdCode(Integer id);

    void UpPssByCode(@Param("id") Integer id,@Param("telephone") String telephone,@Param("newPass") String newPass);
}
