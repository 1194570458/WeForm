package com.weform.exception;

import com.weform.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:08
 */
@Getter
public class FormException extends RuntimeException{

    private Integer code;

    public FormException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public FormException(ResultEnum resultEnum,String message) {
        super(message);
        this.code = resultEnum.getCode();
    }
}
