package com.obtk;

import com.obtk.util.jwt.JwtUtil;
import com.obtk.util.sendEmail.MailUtils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SpringbootvueApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    private MailUtils mailUtils;

    @Test
    void contextLoads() {
        //加密
        String username = stringEncryptor.encrypt("root");
        System.out.println("username :"+username);
        String password = stringEncryptor.encrypt("fu20010412");
        System.out.println("password :"+password);
    }


    @Test
    public void Test2(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username","123wqeeqwe");
        map.put("password","qweqewqqzsda");
        String token = JwtUtil.getToken(map);
        System.out.println("token = " + token);
    }

    @Test
    public void Test3(){
        mailUtils.sendEmail("2414690715@qq.com","3271758240@qq.com","测试","<h1 style='color:red'>helloWorld</h1>");
    }

}
