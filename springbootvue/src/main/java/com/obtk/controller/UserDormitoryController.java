package com.obtk.controller;

import com.obtk.bean.CommentInfo;
import com.obtk.bean.Equipment;
import com.obtk.service.UserDormitoryService;
import com.obtk.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping
@Controller
public class UserDormitoryController {

    @Autowired
    private UserDormitoryService service;

    @PostMapping("/UserSignIn.do")
    @ResponseBody
    public String UserSignIn(HttpServletRequest request , String sigInTime ,  String leaveInfo){

        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        Boolean flag = service.UserSignIn(sigInTime,id,leaveInfo);
        if (flag){
            return "success";
        }
        return "error";
    }

    /**
     * 插入维修信息
     * @param request
     * @param reminderInfo 维修信息
     * @param reminderTime 维修时间
     * @param dormitoryID 寝室号
     * @return
     */
    @PostMapping("/MaintenanceReport.do")
    @ResponseBody
    public String MaintenanceReport(HttpServletRequest request,String reminderInfo,String reminderTime,Integer dormitoryID,Integer equipmentkey){
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        Boolean flag = service.MaintenanceReport(id,reminderInfo,reminderTime,dormitoryID,equipmentkey);
        if (flag){
            return "success";
        }
        return "error";
    }

    /**
     * 根据寝室号查询对应的维修信息
     * @param dormitoryID 寝室号
     * @return
     */
    @PostMapping("/findReportByDormitoryID.do")
    @ResponseBody
    public List<Equipment> findReportBydormitoryID(Integer dormitoryID){
        List<Equipment> list = service.findReportBydormitoryID(dormitoryID);
        return list;
    }


    /**
     * 根据寝室id更改设备维修状态
     * @param dormitoryID 寝室id
     * @return
     */
    @GetMapping("/updateEquipmentState.do")
    @ResponseBody
    public String updateEquipmentState(Integer dormitoryID){
        Boolean flag =  service.updateEquipmentState(dormitoryID);
        if (flag){
            return "success";
        }
        return "error";
    }

    /**
     * 评价
     * @param dormitoryID
     * @param commentInfo
     * @param equipmentkey
     * @return
     */
    @GetMapping("/updateComment.do")
    @ResponseBody
    public String updateComment(Integer dormitoryID,String commentInfo,Integer equipmentkey){
        Boolean flag =  service.updateComment(dormitoryID,commentInfo,equipmentkey);
        if (flag){
            return "success";
        }
        return "error";
    }

    /**
     * 评分
     * @param request
     * @param score
     * @return
     */
    @GetMapping("/markScore.do")
    @ResponseBody
    public String markScore(HttpServletRequest request,Integer score){
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        Boolean flag = service.markScore(id,score);
        if (flag){
            return "success";
        }
        return "error";
    }


    @PostMapping("/findAllComment.do")
    @ResponseBody
    public List<CommentInfo> findAllComment(){
        List<CommentInfo> list = service.findAllComment();
        return list;
    }



}

