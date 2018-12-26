package com.weform.service.impl;

import com.weform.utils.ResultVOUtil;
import com.weform.vo.FieldDetailVO;
import com.weform.vo.FieldMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @Author: Kason
 * @Date: 2018/12/13 9:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FieldDataServiceImplTest {

    @Autowired
    private FieldDataServiceImpl fieldDataService;

    @Test
    public void create() {
        FieldMasterVO fieldMasterVO = new FieldMasterVO();
        fieldMasterVO.setFormId("5454");
        fieldMasterVO.setOpenid("56556545");

        ArrayList<FieldDetailVO> fieldDetailVOS = new ArrayList<>();
        FieldDetailVO o1 = new FieldDetailVO();
        o1.setFieldId("1544601352713499656");
        o1.setFormData("asduadhaiudhuiashausdhasduasdhaisudhiau");
        FieldDetailVO o2 = new FieldDetailVO();
        o2.setFieldId("154460150604840793");
        o2.setFormData("asduadhaiudhuiashausdhasduasdhaisudhiau");

        fieldDetailVOS.add(o1);
        fieldDetailVOS.add(o2);
        fieldMasterVO.setData(fieldDetailVOS.toString());

        FieldMasterVO vo = fieldDataService.create(fieldMasterVO);

        log.info("【添加数据】vo={}", ResultVOUtil.success(vo));


    }
}