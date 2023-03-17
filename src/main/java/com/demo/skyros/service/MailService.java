package com.demo.skyros.service;

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

    public CurrencyVO sendMail(CurrencyVO currencyVO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;//true indicates multipart message
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("Currency Report");
            helper.setTo(adminMail);
            helper.setText(prepareMessageBody(currencyVO), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return currencyVO;
    }

    private String prepareMessageBody(CurrencyVO currencyVO) {

        String body = "<html>\n" +
                "  <head>\n" +
                "    <style>\n" +
                "      .colored {\n" +
                "        color: black;\n" +
                "      }\n" +
                "      #body {\n" +
                "        font-size: 15px;\n" +
                "      }\n" +
                "      @media screen and (min-width: 500px) {\n" +
                "        .colored {\n" +
                "          color:red;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"body\">\n" +
                "      <p>Dear Admin,</p>\n" +
                "      <p class=\"colored\">\n" +
                "        Kindly note that currency is changed from " + currencyVO.getFrom() + " to " + currencyVO.getTo() + " with quantity " + currencyVO.getQuantity() + " and conversion multiple = " + currencyVO.getConversionMultiple() + "\n" +
                "        Result : " + currencyVO.getConversionMultiple() + " * " + currencyVO.getQuantity() + " = " + currencyVO.getTotalCalculatedAmount() + "\n" +
                "      <p>Best Regards,</p>\n" +
                "      <p>Ahmed Baz</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";

        return body;
    }
}
