package com.demo.skyros.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyVO {

    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal totalCalculatedAmount;
    private BigDecimal quantity;

}
