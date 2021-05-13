package com.obtk;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootvueApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {
        //加密
        String username = stringEncryptor.encrypt("root");
        System.out.println("username :"+username);
        String password = stringEncryptor.encrypt("fu20010412");
        System.out.println("password :"+password);
    }

}
