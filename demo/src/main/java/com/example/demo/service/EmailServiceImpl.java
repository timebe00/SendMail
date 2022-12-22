package com.example.demo.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;
import java.util.Random;

@Log
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public String sendEmail(String email) throws Exception {
        String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        Random random = new Random();
        String px = "";

        if(!Pattern.matches(emailRegex, email)) {
            return "이메일 형식이 아닙니다.";
        }

        //  인증번호
        for(int i = 0; i < 6; i ++) {
            px += random.nextInt(10);
        }

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("GoodJob 회원가입 이메일 인증");

        String meg = "인증번호 : " + px;

        message.setText(meg, "utf-8", "html");
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("an0160@naver.com", "오일머니"));// 보내는 사람

        emailSender.send(message);

        // 인증번호 DB에 저장
        return "이메일을 보냈습니다.";
    }
}
