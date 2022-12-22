package com.example.demo.controller;

import com.example.demo.service.EmailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class EmailController {
    @Autowired
    private EmailService service;

    @PostMapping("sendEmail")
    public ResponseEntity<String> sendEmail() {
        try {
            String sendEmail = service.sendEmail("an0160@naver.com");
            return new ResponseEntity<String>(sendEmail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("전송 실패하였습니다.", HttpStatus.NO_CONTENT);
        }
    }
}
