package com.demo.skyros.service;

import com.demo.skyros.vo.UserVO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthTemplateService {


    private static final String FOOTER =
            " <p> BR,</p>\n" +
                    " <p> App Admin </p>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";
    private static final String HEADER = "<html>\n" +
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
            "    <div id=\"body\">\n";

    public String prepareMessageForAccountActivation(UserVO userVO) {
        String body = "<p>Dear " + userVO.getUserName() + ",</p>\n" +
                "<p class=\"colored\"> Kindly use OTP " + userVO.getOtp() + " to activate your account </p> \n";
        StringBuilder builder = new StringBuilder(HEADER);
        builder.append(body);
        builder.append(FOOTER);
        return builder.toString();
    }
}
