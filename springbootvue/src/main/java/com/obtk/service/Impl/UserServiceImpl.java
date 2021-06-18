package com.obtk.service.Impl;

import com.obtk.bean.*;
import com.obtk.mapper.UserDao;
import com.obtk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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
    public List<User> findAll(Integer id,Integer personalId) {
        List<User> list = null;
        try {
            list = dao.findAll(id,personalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    @Transactional
    public Boolean updateUser(User user,Integer id) {
        Boolean flag = false;
        try {
            dao.updateUser(user,id);
            flag =true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    @Transactional
    public Boolean updatePass(Integer id, String newPass) {
        Boolean flag = false;
        try {
            dao.updatePass(id,newPass);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Mz> findAllMz() {
        //查询缓存中的数据
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.boundZSetOps("mz").rangeWithScores(0, -1);
        List<Mz> list = null;
        if (tuples==null||tuples.size()==0){
            //缓存为空，先查询数据库
            list = dao.findAllMz();
            //将查询到的结果添加到缓存当中
            for (int i=0;i<list.size();i++){
                redisTemplate.boundZSetOps("mz").add(list.get(i).getName(),list.get(i).getId());
            }
        }else {
            //缓存有值从缓存查
            list = new ArrayList<Mz>();
            for (ZSetOperations.TypedTuple<String> tuple : tuples){
                Mz mz = new Mz();
                mz.setId(tuple.getScore().intValue());
                mz.setName(tuple.getValue());
                list.add(mz);
            }
        }
        return list;
    }

    @Override
    public List<UserInfo> findByPostUser(int id) {
        return dao.findByPostUser(id);
    }

    @Override
    public List<User> findByTelephoneUser(String telephone) {
        return dao.findByTelephoneUser(telephone);
    }

    @Override
    public List<UpPass> findByIdCode(Integer id) {
        return dao.findByIdCode(id);
    }

    @Override
    @Transactional
    public Boolean UpPssByCode(Integer id, String telephone,String newPass) {
        Boolean flag = false;
        try {
            dao.UpPssByCode(id,telephone,newPass);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
