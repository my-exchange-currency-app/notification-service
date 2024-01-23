package com.demo.skyros.vo;

import lombok.*;

import java.util.Map;


@Setter
@Getter
public class MailVO {

    private String subject;
    private String from;
    private String to;
    private Map<String, Object> model;
    private String template;

}
