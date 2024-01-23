package com.demo.skyros.controller;

import com.demo.skyros.service.MailService;
import com.demo.skyros.vo.MailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody MailVO vo) {
        mailService.sendEmail(vo);
    }

}
