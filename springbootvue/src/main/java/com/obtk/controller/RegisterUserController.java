package com.obtk.controller;

import com.obtk.bean.Dormitory;
import com.obtk.bean.DormitoryArea;
import com.obtk.bean.User;
import com.obtk.bean.administratorCode;
import com.obtk.service.RegisterUserService;
import com.obtk.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping
@Controller
public class RegisterUserController {

    @Autowired
    private RegisterUserService service;


    /**
     * 注册码
     * @return
     */
    @GetMapping("/findAllCode.do")
    @ResponseBody
    public List<administratorCode> findAllAdministratorCode(){
        List<administratorCode> list = service.findAllCode();
        return list;
    }

    /**
     * 用户注册
     * @param user
     * @param checkCode
     * @param request
     * @return
     */
    @PostMapping("/register.do")
    @ResponseBody
    public Map<String,Object> registerUser(User user, String checkCode, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        //验证验证码
        log.info(checkCode);
        HttpSession session = request.getSession();
        String checkCode_session =(String) session.getAttribute("checkCode_session");
        if(checkCode != null && !checkCode_session.equalsIgnoreCase(checkCode)){
            //验证码错误
            map.put("checkCode",false);
        }else {
            //验证码正确
            map.put("checkCode",true);
            Boolean flag = service.register(user);
            map.put("msg",flag);
        }
        return map;
    }


    @PostMapping("/insertImage.do")
    @ResponseBody
    public void insertImage(HttpServletRequest request,Integer id){
        service.insertImage(id);
    }

    @GetMapping("/findUserId.do")
    @ResponseBody
    public Map<String,Object> findUserId(String username , String password){
        HashMap<String, Object> map = new HashMap<>();
        User user = service.findUserID(username,password);
        map.put("user",user);
        return map;
    }

    @PostMapping("/findAllDormitoryArea.do")
    @ResponseBody
    public List<DormitoryArea> findAllDormitoryArea(){
        List<DormitoryArea> list = service.findAllDormitoryArea();
        return list;
    }
    
    @GetMapping("/updateDormitoryArea.do")
    @ResponseBody
    public String updateDormitoryArea(Integer id,Integer optionsId){
        Boolean flag = service.updateDormitoryArea(id,optionsId);
        if (flag){
            return "success";
        }
        return "error";
    }


    /**
     * 查找所有的寝室号
     * @return
     */
    @PostMapping("/findAllDormitory.do")
    @ResponseBody
    public List<Dormitory> findAllDormitory(){
        List<Dormitory> list = service.findAllDormitory();
        return list;
    }

    /**
     * 插入学生对应的寝室
     * @param id 用户id
     * @param optionsId 寝室id
     * @return
     */
    @GetMapping("/insertStudentDormitory.do")
    @ResponseBody
    public String insertStudentDormitory(Integer id , Integer optionsId){
        Boolean flag = service.insertStudentDormitory(id,optionsId);
        if (flag){
            return "success";
        }
        return "error";
    }


}