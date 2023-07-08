package com.example.demo.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
    @Autowired
    private JavaMailSenderImpl sender;

    @Value("${spring.mail.properties.from}")
    private String from;
    @Value("${spring.mail.properties.from}")
    private String to;

    @GetMapping("/mail/send")
    public String sendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("spring boot 发送邮件");
        simpleMailMessage.setText("我的第一封邮件！！！");
        sender.send(simpleMailMessage);
        return "success";
    }
}
