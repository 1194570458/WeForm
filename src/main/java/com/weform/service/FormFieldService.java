package com.weform.service;

import com.weform.entity.FormField;
import com.weform.vo.FormFieldVO;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:01
 */
public interface FormFieldService {

    //添加字段
    FormField save(FormField formField,String openid);

    //查询字段
    List<FormFieldVO> list(String formId);

    //删除字段
    void delField(String fieldId,String openid);


    void delAllField(String formId, String value);
}
