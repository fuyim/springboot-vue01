package com.obtk.mapper;

import com.obtk.bean.ImageFile;
import com.obtk.bean.Mz;
import com.obtk.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UserDao {

    public User login(@Param("telephoneEmail") String telephoneEmail,@Param("password") String password);

    Boolean updateImage(@Param("id") Integer id, @Param("imageUrl") String imageUrl, @Param("fileName") String fileName);

    ImageFile getByIdImage(Integer id);

    List<User> findAll(Integer id);

    void updateUser(@Param("user") User user,@Param("id") Integer id);

    void updatePass(@Param("id") Integer id, @Param("newPass") String newPass);

    List<Mz> findAllMz();
}
