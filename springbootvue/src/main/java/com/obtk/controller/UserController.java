package com.obtk.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.obtk.bean.ImageFile;
import com.obtk.bean.Mz;
import com.obtk.bean.User;
import com.obtk.service.UserService;
import com.obtk.util.cookie.CookieUtils;
import com.obtk.util.jwt.JwtUtil;
import com.obtk.util.updataPhoto.UpdataPhotoNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 用戶登錄
     * @param telephoneEmail 電話號碼或郵箱
     * @param password 密碼
     * @param checkCode 驗證碼
     * @param request
     * @return
     */
    @GetMapping("/login.do")
    @ResponseBody
    public Map<String,Object> login(String telephoneEmail, String password , String checkCode, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map = new HashMap<>();
        log.info(telephoneEmail);
        log.info(password);
        log.info(checkCode);
        //验证验证码
        HttpSession session = request.getSession();
        String checkCode_session =(String) session.getAttribute("checkCode_session");
        if(checkCode != null && !checkCode_session.equalsIgnoreCase(checkCode)){
            //验证码不正确
            map.put("checkCode",false);
        }else {
            map.put("checkCode",true);
            //生成token
            try {
                HashMap<String, String> payload = new HashMap<>();
                //调用service
                User user = service.login(telephoneEmail, password);
                Integer id = user.getId();
                log.info(id.toString());
                payload.put("id",id.toString());
//                payload.put("telephoneEmail",telephoneEmail);
//                payload.put("password",password);
                String token = JwtUtil.getToken(payload);
                map.put("token",token);
                //判断缓存中是否存在token
                //将token存入缓存
                Boolean flag  = redisTemplate.hasKey("token");
                if (!flag){
                    //不存在将token存入缓存中
                   redisTemplate.opsForValue().set("token",token);
                }
                if (user!=null){
                    map.put("msg",true);
                }else {
                    map.put("msg",false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    /**
     *用戶頭像的上傳
     * @param file 以表单的形式进行文件上传
     * @param request
     * @return
     */
    @PostMapping("/upload.do")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            //获取前台传过来图片的名称
            String imageFileName = file.getOriginalFilename();
            String fileName = UpdataPhotoNameUtils.getPhotoName("img", imageFileName);
            System.out.println(fileName);
            //获取字节数
            byte[] bytes = file.getBytes();
            String url = "D:\\idea-work\\vue-springboot-project01\\vueproject\\static\\img\\"+fileName;
            Path path = Paths.get(url);
            String ImageUrl = path.toString();
            //写入文件
            Files.write(path,bytes);
            //从请求头中获取token
            String token = request.getHeader("Authentication-Token");
            int id = JwtUtil.getParams(token);
            System.out.println(id);
            Boolean flag = service.updateImage(id,ImageUrl,fileName);
            //判斷是否修改成功
            if (flag){
                map.put("msg","success");
                map.put("token",true);
            }else {
                map.put("msg","error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("token",false);
        }
        return map;
    }

    /**
     * 通过id查询用户图片
     * @param request
     * @return
     */
    @GetMapping("/showImage.do")
    @ResponseBody
    public Map<String,Object> getByIdImage(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //进行token验证
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        System.out.println(id);
        ImageFile imageFile = service.getByIdImage(id);
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

    /**
     * 查询所有用户将数据渲染到前台展示
     * @return
     */
    @GetMapping("/findByIDUser.do")
    @ResponseBody
    public List<User> findAll(HttpServletRequest request){
        //进行token验证
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        List<User> list = service.findAll(id);
        return list;
    }

    @PostMapping("/updateUser.do")
    @ResponseBody
    public String updateUser(HttpServletRequest request,User user){
        System.out.println(user);
        //进行token验证
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        System.out.println(user.getEmail());
        Boolean flag = service.updateUser(user,id);
        if (flag){
            return "success";
        }
        return "error";
    }

    @GetMapping("/updatePass.do")
    @ResponseBody
    public Map<String,Object> updatePass(HttpServletRequest request,String newPass,String checkCode){
        HashMap<String, Object> map = new HashMap<>();
        //获取id
        String token = request.getHeader("Authentication-Token");
        int id = JwtUtil.getParams(token);
        //验证验证码
        HttpSession session = request.getSession();
        String checkCode_session =(String) session.getAttribute("checkCode_session");
        if(checkCode != null && !checkCode_session.equalsIgnoreCase(checkCode)){
            map.put("checkCode",false);
        }else {
            Boolean flag = service.updatePass(id,newPass);
            map.put("msg",flag);
        }
        return map;
    }

    @GetMapping("/findAllMz.do")
    @ResponseBody
    public List<Mz> findAllMz(HttpServletRequest request){
        List<Mz> list = service.findAllMz();
        return list;
    }

}
