package com.weform.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.vo.FieldDetailVO;
import com.weform.vo.FieldMasterVO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/13 10:43
 * json转换成对象
 */
@Slf4j
public class Data2FieldDataVO {

    public static List<FieldDetailVO> data2FieldDataVOList(FieldMasterVO fieldMasterVO) {
        Gson gson = new Gson();
        ArrayList<FieldDetailVO> fieldDetailVOArrayList;
        try {
            fieldDetailVOArrayList = gson.fromJson(fieldMasterVO.getData(), new TypeToken<List<FieldDetailVO>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误,json={}", fieldMasterVO.getData());
            throw new FormException(ResultEnum.PARAM_ERROR);
        }
        return fieldDetailVOArrayList;
    }

}
