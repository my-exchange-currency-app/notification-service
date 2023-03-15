package com.demo.skyros.service;

import com.demo.skyros.vo.CurrencyExchangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CurrencyMailService {

    @Value("#${app.admin.mail}")
    private String adminMail;

    @Autowired
    private JavaMailSender javaMailSender;

    public CurrencyExchangeVO sendMail(CurrencyExchangeVO currencyExchangeVO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;//true indicates multipart message
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("Currency Report");
            helper.setTo(adminMail);
            helper.setText(prepareMessageBody(currencyExchangeVO), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return currencyExchangeVO;
    }

    private String prepareMessageBody(CurrencyExchangeVO currencyExchangeVO) {

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
                "        Kindly note that currency is changed from " + currencyExchangeVO.getFrom() + " to " + currencyExchangeVO.getTo() + " with quantity " + currencyExchangeVO.getQuantity() + " and conversion multiple = " + currencyExchangeVO.getConversionMultiple() + "\n" +
                "        Result : " + currencyExchangeVO.getConversionMultiple() + " * " + currencyExchangeVO.getQuantity() + " = " + currencyExchangeVO.getTotalCalculatedAmount() + "\n" +
                "      <p>Best Regards,</p>\n" +
                "      <p>Ahmed Baz</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";

        return body;
    }
}
