package com.obtk.mapper;

import com.obtk.bean.Dormitory;
import com.obtk.bean.DormitoryArea;
import com.obtk.bean.User;
import com.obtk.bean.administratorCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface RegisterUserDao {
    List<administratorCode> findAllCode();

    void register(@Param("user") User user);

    void insertImage(Integer id);

    User finUserID(@Param("username") String username, @Param("password") String password);

    List<DormitoryArea> findAllDormitoryArea();

    Boolean updateDormitoryArea(@Param("id") Integer id,@Param("optionsId") Integer optionsId);

    List<Dormitory> findAllDormitory();

    void insertStudentDormitory(@Param("id") Integer id,@Param("optionsId") Integer optionsId);
}
