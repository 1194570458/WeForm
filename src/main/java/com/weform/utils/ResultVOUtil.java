package com.weform.utils;

import com.weform.vo.ResultVO;

/**
 * @Author: Kason
 * @Date: 2018/12/11 18:25
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }

    public static ResultVO loginError() {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg("登陆过期");
        resultVO.setCode(-99);
        return resultVO;
    }

    public static ResultVO success() {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(null);
        return resultVO;
    }

}
