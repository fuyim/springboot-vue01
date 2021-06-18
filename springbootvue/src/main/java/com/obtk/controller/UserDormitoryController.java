package com.obtk.controller;

import com.obtk.service.UserDormitoryService;
import com.obtk.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping
@Controller
public class UserDormitoryController {

    @Autowired
    private UserDormitoryService service;

    @PostMapping("/UserSignIn.do")
    @ResponseBody
    public String UserSignIn(HttpServletRequest request , String sigInTime ,  String leaveInfo){
        //进行token验证
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        Boolean flag = service.UserSignIn(sigInTime,id,leaveInfo);
        if (flag){
            return "success";
        }
        return "error";
    }



}
