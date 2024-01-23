package com.demo.skyros.service;

import com.demo.skyros.vo.MailVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;


@Service
@Log4j2
public class MailServiceImpl implements MailService {

    @Value("#${app.admin.mail}")
    private String adminMail;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration config;

    @Override
    public void sendEmail(MailVO mailVO) {
        try {
            Template template = config.getTemplate(mailVO.getTemplate());
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailVO.getModel());
            log.info(text);
            sendMail(mailVO.getSubject(), text, mailVO.getTo());
        } catch (Exception ex) {
            log.error("sendMail failed", ex);
        }
    }

    private void sendMail(String subject, String text, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setFrom(adminMail);
            helper.setTo(to);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception ex) {
            log.error("sendMail failed", ex);
        }
    }

}
