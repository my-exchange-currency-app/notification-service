package com.demo.skyros.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyReportVO {

    private List<CurrencyVO> currencyExchangeVOList;
    private Date from;
    private Date to;


}
