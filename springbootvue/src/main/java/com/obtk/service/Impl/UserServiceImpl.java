package com.obtk.service.Impl;

import com.obtk.bean.ImageFile;
import com.obtk.bean.User;
import com.obtk.mapper.UserDao;
import com.obtk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    /**
     * 登录操作
     * @param telephoneEmail 手机号或邮箱
     * @param password 密码
     * @return User
     */
    @Override
    public User login(String telephoneEmail, String password) {
        User user = null;
        try {
            user = dao.login(telephoneEmail, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user!=null){
            return user;
        }

        return null;
    }

    /**
     * 用戶头像的更换
     * @param id  用户外键id
     * @param imageUrl   图片在本地的地址
     * @param fileName   图片名称
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean updateImage(Integer id, String imageUrl, String fileName) {
        Boolean flag = false;
        try {
            flag = dao.updateImage(id,imageUrl,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 根据id值来查询头像
     * @param id
     * @return
     */
    @Override
    public ImageFile getByIdImage(Integer id) {
        ImageFile imageFile = null ;
        try {
            imageFile = dao.getByIdImage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    /**
     * 查询所有用户将数据渲染到前台展示
     * @return
     */
    @Override
    public List<User> findAll(Integer id) {
        List<User> list = null;
        try {
            list = dao.findAll(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
