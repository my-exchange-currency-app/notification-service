package com.demo.skyros.controller;

import com.demo.skyros.service.CurrencyMailService;
import com.demo.skyros.vo.CurrencyExchangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyMailController {

    @Autowired
    private CurrencyMailService currencyMailService;


    @PostMapping("sendMail")
    public CurrencyExchangeVO sendMail(@RequestBody CurrencyExchangeVO currencyExchangeVO) {
        return currencyMailService.sendMail(currencyExchangeVO);
    }
}
