package com.demo.skyros.service;

import com.demo.skyros.vo.CurrencyReportVO;
import com.demo.skyros.vo.CurrencyVO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReportTemplateService {

    private static final String FOOTER =
            " <p> Best Regards,</p>\n" +
                    " <p> Ahmed Baz </p>\n" +
                    " <p> Software Engineer </p>\n" +
                    " <p> Mob No: +201009894481 </p>\n" +
                    " <p> Address: Building 143, Smart Village </p>\n" +
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
            "    <div id=\"body\">\n" +
            "      <p>Dear Admin,</p>\n";

    public String prepareMessageBodyForTransaction(CurrencyVO currencyVO) {

        String body = "<p class=\"colored\"> Kindly find the below transaction </p> \n";
        StringBuilder builder = new StringBuilder(HEADER);
        builder.append(body);

        String table = "<table width='100%' border='1' align='center'>"
                + "<tr align='center'>"
                + "<td> # </td>"
                + "<td> From </td>"
                + "<td> To </td>"
                + "<td> Conversion Multiple </td>"
                + "<td> Quantity </td>"
                + "<td> Total Calculated Amount</td>"
                + "</tr>"
                + "<tr align='center'>"
                + "<td> 1 </td>"
                + "<td> " + currencyVO.getFrom() + " </td>"
                + "<td> " + currencyVO.getTo() + " </td>"
                + "<td> " + currencyVO.getConversionMultiple() + " </td>"
                + "<td> " + currencyVO.getQuantity() + " </td>"
                + "<td> " + currencyVO.getTotalCalculatedAmount() + " </td>"
                + "</tr>";

        builder.append(table);
        String tableFooter = "</table>";
        builder.append(tableFooter);
        builder.append(FOOTER);

        return builder.toString();
    }

    public String prepareMessageBodyForTransactionReport(CurrencyReportVO currencyReportVO) {
        String body = "<p class=\"colored\"> Kindly find the below transactions from " + currencyReportVO.getFrom() + ", to " + currencyReportVO.getTo() + " for below: </p> </p> \n";
        StringBuilder builder = new StringBuilder(HEADER);
        builder.append(body);

        String tableHeader = "<table width='100%' border='1' align='center'>"
                + "<tr align='center'>"
                + "<td> # </td>"
                + "<td> From </td>"
                + "<td> To </td>"
                + "<td> Quantity </td>"
                + "</tr>";

        builder.append(tableHeader);

        String tableBody;
        int i = 1;
        for (CurrencyVO vo : currencyReportVO.getCurrencyExchangeVOList()) {
            tableBody = "<tr align='center'>"
                    + "<td>" + (i++) + "</td>"
                    + "<td>" + vo.getFrom() + "</td>"
                    + "<td>" + vo.getTo() + "</td>"
                    + "<td>" + vo.getQuantity() + "</td>"
                    + "</tr>";
            builder.append(tableBody);
        }

        String tableFooter = "</table>";
        builder.append(tableFooter);
        builder.append(FOOTER);
        return builder.toString();
    }
}
