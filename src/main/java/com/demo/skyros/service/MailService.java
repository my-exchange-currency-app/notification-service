package com.demo.skyros.service;

import com.demo.skyros.vo.CurrencyReportVO;
import com.demo.skyros.vo.CurrencyVO;
import com.demo.skyros.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Value("#${app.admin.mail}")
    private String adminMail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTransactionMail(CurrencyVO currencyVO) {
        String text = ReportTemplateService.prepareMessageBodyForTransaction(currencyVO);
        sendMail("Currency Report", text, adminMail);
    }

    public void sendTransactionsReportMail(CurrencyReportVO currencyReportVO) {
        String text = ReportTemplateService.prepareMessageBodyForTransactionReport(currencyReportVO);
        sendMail("Transactions Report", text, adminMail);
    }

    public void sendAccountActivationMail(UserVO userVO) {
        String text = AuthTemplateService.prepareMessageForAccountActivation(userVO);
        sendMail("Account Activation", text, userVO.getEmail());
    }

    public void sendMail(String subject, String text, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
