package com.weform.enums;

import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:10
 */
@Getter
public enum  ResultEnum {
    PARAM_ERROR(10,"参数错误"),
    FORM_NOT_EXIST(11,"表单不存在"),
    FIELD_NOT_EXIST(12,"字段不存在"),
    FORM_NOT_START(13,"表单不可编辑"),
    COOKIE_ERROR(14,"用户信息不存在"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
