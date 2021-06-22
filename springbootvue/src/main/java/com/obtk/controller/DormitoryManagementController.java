package com.obtk.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.obtk.bean.*;
import com.obtk.service.DormitoryManagementService;
import com.obtk.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping
@Controller
public class DormitoryManagementController {

    @Autowired
    private DormitoryManagementService service;


    @PostMapping("/findByDormitoryArea.do")
    @ResponseBody
    public List<DormitoryArea> findByDormitoryArea(HttpServletRequest request){
        //获取id
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        log.info("id为"+id);
        List<DormitoryArea> list = service.findByDormitoryArea(id);
        return list;
    }

    @GetMapping("/findPersonal.do")
    @ResponseBody
    public Map<String,Object> findPersonal(Integer id){
        List<PersonalInfo> list = service.findPersonal(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    //插入重置密码
    @PostMapping("/insertIntoUpPass.do")
    @ResponseBody
    public String insertIntoUpPass(Integer id , String text){
        Boolean flag = service.insertIntoUpPass(id,text);
        if (flag){
            return "success";
        }
        return "error";
    }

    //定期删除重置密码
    @GetMapping("/deleteUpPass.do")
    @ResponseBody
    public String deleteUpPass(Integer id){
        Boolean flag = service.deleteUpPass(id);
        if (flag){
            return "success";
        }
        return "error";
    }

    /**
     * personal分页
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @PostMapping("/queryPage.do")
    @ResponseBody
    public PageInfo<PersonalInfo> getQueryPage(Integer pageNum , Integer pageSize,Integer id){
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<PersonalInfo> list = service.findPersonal(id);
        PageInfo<PersonalInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @PostMapping("/findByPersonalId.do")
    @ResponseBody
    public List<UserInfo> findByPersonalId(Integer post , HttpServletRequest request){
        //获取id
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        List<UserInfo> list = service.findByPersonalId(post,id);
        return list;
    }

    /**
     * 根据id来查询对应的头像
     * @return
     */
    @GetMapping("/findByIdImage.do")
    @ResponseBody
    public Map<String, Object> findByIdImage(Integer id){
        Map<String, Object> map = new HashMap<>();

        ImageFile imageFile = service.findByIdImage(id);
        if (imageFile!=null){
            map.put("imageFile",imageFile);
            map.put("token",true);
            map.put("msg",true);
            map.put("id",imageFile.getId());
        }else {
            map.put("token",false);
            map.put("msg",false);
        }
        return map;
    }

//    @GetMapping("/findByPersonal.do")
//    @ResponseBody
//    public List<User> findByPersonalId(Integer id){
//
//        List<User> list = service.findByPersonal(id);
//        return list;
//    }

    /**
     * 根据寝室id查询对应寝室的人员数
     * @param dormitoryID
     * @return
     */
    @GetMapping("/findByDormitoryIDUser.do")
    @ResponseBody
    public List<UserInfo> findByDormitoryIDUser(Integer dormitoryID){
        List<UserInfo> list = service.findByDormitoryIDUser(dormitoryID);
        return list;
    }

    /**
     * 根据寝室区域号查询对应管理人员
     * @param dormitoryAreaID
     * @return
     */
    @PostMapping("/findByDormitoryAreaID.do")
    @ResponseBody
    public List<UserInfo> findByDormitoryAreaID(Integer dormitoryAreaID){
        List<UserInfo> list = service.findByDormitoryAreaID(dormitoryAreaID);
        return list;
    }

    /**
     * 根据区域id来查找区域管理员管理的寝室
     * @param dormitoryAreaID
     * @return
     */
    @PostMapping("/findByDormitoryByID.do")
    @ResponseBody
    public List<UserInfo> findByDormitoryByID(Integer dormitoryAreaID){
        List<UserInfo> list = service.findByDormitoryByID(dormitoryAreaID);
        return list;
    }

    @PostMapping("/findSignInByDormitoryID.do")
    @ResponseBody
    public List<UserInfo> findSignInByDormitoryID(Integer DormitoryID){
        List<UserInfo> list = service.findSignInByDormitoryID(DormitoryID);
        return list;
    }

    @PostMapping("/queryPageSignIn.do")
    @ResponseBody
    public PageInfo<UserInfo> queryPageSignIn(Integer DormitoryID,Integer pageNum , Integer pageSize){
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = service.findSignInByDormitoryID(DormitoryID);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 查询分数
     * @return
     */
    @PostMapping("/findScore.do")
    @ResponseBody
    public Integer findScore(){
        Integer score = service.findScore();
        return score;
    }

    @PostMapping("/findEquipmentState.do")
    @ResponseBody
    public List<Equipment> findEquipmentState(Integer dormitoryAreaID){
        List<Equipment> list = service.findEquipmentState(dormitoryAreaID);
        return list;
    }

}
