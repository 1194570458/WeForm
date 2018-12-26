package com.weform.service.impl;

import com.weform.entity.FormMaster;
import com.weform.enums.DetailStatusEnum;
import com.weform.enums.FormStatusEnum;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.repository.FieldMasterRepository;
import com.weform.repository.FormMasterRepository;
import com.weform.service.FormMasterService;
import com.weform.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:05
 */
@Slf4j
@Service
public class FormMasterServiceImpl implements FormMasterService {

    @Autowired
    private FormMasterRepository formMasterRepository;

    @Autowired
    private FieldMasterRepository fieldMasterRepository;

    @Override
    public Page<FormMaster> findListNotEqualsStatus(String openid, Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Page<FormMaster> masters = formMasterRepository.findByFormStatusNotAndOpenid(-1, openid, request);

        Iterator<FormMaster> iterator = masters.iterator();
        while (iterator.hasNext()) {
            FormMaster next = iterator.next();
            String formId = next.getFormId();
            Long aLong = fieldMasterRepository.countByFormIdAndDetailStatusNot(formId, DetailStatusEnum.DEL.getCode());
            next.setDataAmount(aLong);
        }
        return masters;
    }

    @Override
    public FormMaster save(FormMaster formMaster) {
        String primaryKey;
        if (formMaster.getFormId() == null) {
            primaryKey = KeyUtil.createPrimaryKey();
            formMaster.setFormId(primaryKey);
        } else {
            FormMaster result = formMasterRepository.findByFormIdAndFormStatusNot(formMaster.getFormId(), new Integer(-1));
            if (!formMaster.getOpenid().equals(result.getOpenid())) {
                log.error("【保存表单】openid错误 formMaster={},result={}", formMaster, result);
                throw new FormException(ResultEnum.PARAM_ERROR);
            }
        }
        if (StringUtils.isEmpty(formMaster.getFormName())) {
            log.error("【创建表单】参数错误 formMaster={}", formMaster);
            throw new FormException(ResultEnum.PARAM_ERROR);
        }
        FormMaster result = formMasterRepository.save(formMaster);
        return result;
    }

    @Override
    public void changeSatus(FormMaster formMaster) {
        FormMaster result = formMasterRepository.findById(formMaster.getFormId()).get();
        if (result == null) {
            log.error("【修改表单状态】表单不存在 formMaster={}", formMaster);
            throw new FormException(ResultEnum.FORM_NOT_EXIST);
        }
        if (!formMaster.getOpenid().equals(result.getOpenid())) {
            log.error("【修改表单状态】openid不匹配");
            throw new FormException(ResultEnum.PARAM_ERROR);
        }
        result.setFormStatus(formMaster.getFormStatus());
        BeanUtils.copyProperties(result, formMaster);
        formMasterRepository.save(formMaster);
    }

    @Override
    public void delForm(FormMaster formMaster) {
        formMaster.setFormStatus(-1);
        changeSatus(formMaster);
    }
}
