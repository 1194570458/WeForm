package com.weform.enums;

import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/12 16:48
 */
@Getter
public enum DetailStatusEnum {

    DEL(-1, "已删除"),
    TAB(1, "已标记"),
    UNTAN(0, "未标记"),


    ;

    private Integer code;

    private String msg;

    DetailStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
