package com.weform.enums;

import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/12 8:55
 */
@Getter
public enum FieldStatusEnum {

    USED(0,"正在使用"),
    DEL(1,"已删除")
    ;

    private Integer code;

    private String msg;

    FieldStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
