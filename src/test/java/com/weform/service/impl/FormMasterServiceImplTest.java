package com.weform.service.impl;

import com.weform.entity.FormMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FormMasterServiceImplTest {

    @Autowired
    private FormMasterServiceImpl formMasterService;

    @Test
    public void findUndelAll() {
        Page<FormMaster> undelAll = formMasterService.findListNotEqualsStatus("56556545", 0,5);

        Assert.assertNotEquals(0,undelAll.getTotalElements());

    }

    @Test
    public void save() {
        FormMaster formMaster = new FormMaster();
        formMaster.setFormId("2342");
        formMaster.setOpenid("56556545");
        formMaster.setFormName("表单1");

        FormMaster result = formMasterService.save(formMaster);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void del() {
        FormMaster formMaster = new FormMaster();
        formMaster.setOpenid("56556545");
        formMaster.setFormId("2342");
        formMaster.setFormStatus(-1);
        formMasterService.changeSatus(formMaster);
    }
}