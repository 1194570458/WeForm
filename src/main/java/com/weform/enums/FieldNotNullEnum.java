package com.weform.enums;

import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/24 19:07
 */
@Getter
public enum  FieldNotNullEnum {
    NULL(0,"可空"),
    NOT_NULL(1,"必填"),
    ;

    private Integer code;
    private String msg;

    FieldNotNullEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
