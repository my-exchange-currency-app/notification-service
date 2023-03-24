package com.demo.skyros.service;

import com.demo.skyros.vo.CurrencyReportVO;
import com.demo.skyros.vo.CurrencyVO;
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

    @Autowired
    private ReportTemplateService reportTemplateService;

    public void sendTransactionMail(CurrencyVO currencyVO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("Currency Report");
            helper.setTo(adminMail);
            helper.setText(getReportTemplateService().prepareMessageBodyForTransaction(currencyVO), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendTransactionsReportMail(CurrencyReportVO currencyReportVO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("Transactions Report");
            helper.setTo(adminMail);
            helper.setText(getReportTemplateService().prepareMessageBodyForTransactionReport(currencyReportVO), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public ReportTemplateService getReportTemplateService() {
        return reportTemplateService;
    }

    public void setReportTemplateService(ReportTemplateService reportTemplateService) {
        this.reportTemplateService = reportTemplateService;
    }
}
