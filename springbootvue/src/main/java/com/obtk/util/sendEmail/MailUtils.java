package com.obtk.util.sendEmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtils {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String from , String to , String subject,String content){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);
            messageHelper.setTo(to);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(from);
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(content);
//        javaMailSender.send(mailMessage);
    }

}
