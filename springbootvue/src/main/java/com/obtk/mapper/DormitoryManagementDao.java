package com.obtk.mapper;

import com.obtk.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormitoryManagementDao {
    List<DormitoryArea> findByDormitoryArea(int id);

    List<PersonalInfo> findPersonal(Integer id);

    void insertIntoUpPass(@Param("id") Integer id,@Param("text") String text);

    void deleteUpPass(Integer id);

    List<UserInfo> findByPersonalIdadministrator(Integer id);

    List<UserInfo> findByPersonalIdUser(Integer id);

    ImageFile findByIdImage(Integer id);

    List<UserInfo> findByDormitoryIDUser(Integer dormitoryID);

    List<UserInfo> findByDormitoryAreaID(Integer dormitoryAreaID);

    List<UserInfo> findByDormitoryByID(Integer dormitoryAreaID);

    List<UserInfo> findSignInByDormitoryID(Integer dormitoryID);

    Integer findScore();

    List<Equipment> findEquipmentState(Integer dormitoryAreaID);

//    List<User> findByPersonal(Integer id);
}
