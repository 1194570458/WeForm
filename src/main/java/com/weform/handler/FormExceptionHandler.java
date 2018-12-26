package com.weform.handler;

import com.weform.exception.FormException;
import com.weform.exception.UserAuthorizeException;
import com.weform.utils.ResultVOUtil;
import com.weform.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

/**
 * @Author: Kason
 * @Date: 2018/12/24 9:12
 */

@ControllerAdvice
public class FormExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = FormException.class)
    public ResultVO handlerException(FormException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = UserAuthorizeException.class)
    public ResultVO authorizeException(){
        return ResultVOUtil.loginError();
    }

}
