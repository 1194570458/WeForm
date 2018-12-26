package com.weform.enums;

import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:39
 *  -1已删除,0未开始,1收集中,2已停止
 */
@Getter
public enum FormStatusEnum {

    DEL(-1,"已删除"),
    NOMAL(0,"未开始"),
    START(1,"收集中"),
    STOP(0,"已停止"),
    ;

    private Integer code;

    private String msg;

    FormStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
