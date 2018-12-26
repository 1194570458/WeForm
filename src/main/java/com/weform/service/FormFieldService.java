package com.weform.service;

import com.weform.entity.FormField;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:01
 */
public interface FormFieldService {

    //添加字段
    FormField save(FormField formField,String openid);

    //查询字段
    List<FormField> list(String formId);

    //删除字段
    void delField(String fieldId,String openid);


}
