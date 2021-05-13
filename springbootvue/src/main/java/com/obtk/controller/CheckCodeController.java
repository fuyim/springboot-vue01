package com.obtk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping
public class CheckCodeController {

    @RequestMapping("/checkCode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 140;
        int height = 60;
        //创建对象，在内存中图片(验证码图片对象)
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //美化图片
        //1.1 填充背景色
        Graphics graphics = image.getGraphics(); // 画笔对象
        graphics.setColor(Color.lightGray);           //设置画笔颜色
        graphics.fillRect(0,0,width,height);
        //1.2画边框
//        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,height-1);


        //1.3写验证字
        Random random = new Random();
        String str = "0123456789abcdefghijklmnABCDEFGHIJKLMN";
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <=4 ; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            int red =random.nextInt(255);
            int gree =random.nextInt(255);
            int yellow =random.nextInt(255);
            Color color = new Color(red,gree,yellow);
            graphics.setColor(color);
            graphics.setFont(new Font("黑体",Font.BOLD,30));
            graphics.drawString(ch+"",(width/5)*i,height/2);
        }

        String checkCode_session = sb.toString();
        //将验证码存入session
        request.getSession().setAttribute("checkCode_session",checkCode_session);

        //1.4画干扰线
        for (int i = 1; i <=10 ; i++) {
            int red =random.nextInt(255);
            int gree =random.nextInt(255);
            int yellow =random.nextInt(255);
            Color color = new Color(red,gree,yellow);
            graphics.setColor(color);
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1,x2,y1,y2);
        }
        //将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

}
