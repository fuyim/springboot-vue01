package com.obtk.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.obtk.bean.*;
import com.obtk.service.DormitoryManagementService;
import com.obtk.util.jwt.JwtUtil;
import com.obtk.util.sendEmail.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    MailUtils mailUtils;


    /**
     * 根据管理员id查找寝室区域信息
     * @param request
     * @return
     */
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

    /**
     * 根据用户id查找用户信息
     * @param id
     * @return
     */
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

    /**
     * 查找维修状态
     * @param dormitoryAreaID
     * @return
     */
    @PostMapping("/findEquipmentState.do")
    @ResponseBody
    public List<Equipment> findEquipmentState(Integer dormitoryAreaID){
        List<Equipment> list = service.findEquipmentState(dormitoryAreaID);
        return list;
    }

    /**
     * 发送电子邮件给3271758240@qq.com
     * @param from 发送者
     * @param subject 标题
     * @param content 内容
     * @return
     */
    @PostMapping("/sendEmail.do")
    @ResponseBody
    public String sendEmail(String from,String subject,String content){
        Boolean flag = false;
        try {
            mailUtils.sendEmail(from,"3271758240@qq.com",subject,content);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){
            return "success";
        }
        return "error";
    }

    /**
     * 查找维修者状态
     * @return
     */
    @PostMapping("/findRecipientsState.do")
    @ResponseBody
    public Recipients findRecipientsState(){
        Recipients recipients =  service.findRecipientsState();
        return recipients;
    }

    /**
     * 修改维修者状态
     * @param state
     * @return
     */
    @GetMapping("/updateRecipientsState.do")
    @ResponseBody
    public String  updateRecipientsState(Integer state){
        Boolean flag = false;
        try {
            service.updateRecipientsState(state);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){
            return "已接受";
        }
        return "error";
    }

    /**
     * 根据寝室区域id查找对应寝室分数信息
     * @param dormitoryAreaID
     * @return
     */
    @PostMapping("/findDormitoryRate.do")
    @ResponseBody
    public List<DormitoryRate> findDormitoryRate(Integer dormitoryAreaID){
        List<DormitoryRate> list = service.findDormitoryRate(dormitoryAreaID);
        return list;
    }

    /**
     * 更改对应寝室的分数
     * @param score
     * @param id
     * @return
     */
    @GetMapping("/updateDormitoryRate.do")
    @ResponseBody
    public String updateDormitoryRate(Integer score , Integer id){
        Boolean flag = service.updateDormitoryRate(score,id);
        if (flag){
            return "success";
        }
        return "error";
    }


}
