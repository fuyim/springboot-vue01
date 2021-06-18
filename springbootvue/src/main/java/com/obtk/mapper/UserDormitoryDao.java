package com.obtk.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDormitoryDao {
    void UserSignIn(@Param("sigInTime") String sigInTime, @Param("id") Integer id,@Param("leaveInfo") String leaveInfo);
}
