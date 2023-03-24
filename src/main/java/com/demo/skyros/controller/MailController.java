package com.demo.skyros.controller;

import com.demo.skyros.service.MailService;
import com.demo.skyros.vo.CurrencyReportVO;
import com.demo.skyros.vo.CurrencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("sendTransactionMail")
    public void sendMail(@RequestBody CurrencyVO currencyVO) {
        mailService.sendTransactionMail(currencyVO);
    }

    @PostMapping("sendReportMail")
    public void sendReportMail(@RequestBody CurrencyReportVO currencyReportVO) {
        mailService.sendTransactionsReportMail(currencyReportVO);
    }
}
