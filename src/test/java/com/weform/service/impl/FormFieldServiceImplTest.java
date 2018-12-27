package com.weform.service.impl;

import com.weform.entity.FormField;
import com.weform.vo.FormFieldVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:26
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class FormFieldServiceImplTest {

    @Autowired
    private FormFieldServiceImpl formFieldService;

    @Test
    public void add() {
        FormField formField = new FormField();
        formField.setFormId("123");
        formField.setFieldType("input");
        formField.setFieldClass("input3");
        formField.setFieldName("name");
        formField.setFieldAttr("src='http:xxxx.jpg'");
        formField.setFieldGrade(0);
        FormField field = formFieldService.save(formField, "56556545");
        Assert.assertNotEquals(null,field);
    }

    @Test
    public void list() {
        List<FormFieldVO> list = formFieldService.list ("123");
        log.info("【list】 = {}" , list);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void delField() {
        formFieldService.delField("1544578571610458234","56556545");
    }

}