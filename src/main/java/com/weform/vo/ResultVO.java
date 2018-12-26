package com.weform.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: Kason
 * @Date: 2018/12/11 18:08
 */
@Data
@ToString
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data ;

}
