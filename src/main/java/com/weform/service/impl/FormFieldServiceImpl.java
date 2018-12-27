package com.weform.service.impl;

import com.weform.entity.FormField;
import com.weform.entity.FormMaster;
import com.weform.enums.FieldStatusEnum;
import com.weform.enums.FormStatusEnum;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.repository.FormFieldRepository;
import com.weform.repository.FormMasterRepository;
import com.weform.service.FormFieldService;
import com.weform.utils.KeyUtil;
import com.weform.vo.FormFieldVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:10
 */
@Service
@Slf4j
public class FormFieldServiceImpl implements FormFieldService {

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Autowired
    private FormMasterRepository formMasterRepository;

    @Override
    @Transactional
    public FormField save(FormField formField, String openid) {
        checkForm(formField.getFormId(), openid);
        if (StringUtils.isEmpty(formField.getFieldId())) {
            String primaryKey = KeyUtil.createPrimaryKey();
            formField.setFieldId(primaryKey);
        } else {
            if (formFieldRepository.findById(formField.getFieldId()).get() == null) {
                log.error("【更新字段】 错误 formField={},openid={}", formField, openid);
                throw new FormException(ResultEnum.FIELD_NOT_EXIST);
            }
        }
        FormField result = formFieldRepository.save(formField);
        return result;
    }

    @Override
    @Transactional
    public List<FormFieldVO> list(String formId) {
        Sort orders = new Sort(Sort.Direction.ASC, "fieldGrade");
        List<FormField> result = formFieldRepository.findByFormIdAndFieldStatus(formId, FieldStatusEnum.USED.getCode(), orders);
        List<FormFieldVO> collect = result.stream().map(e ->
                new FormFieldVO(
                        e.getFieldId(),
                        e.getFormId(),
                        e.getFieldType(),
                        e.getFieldClass(),
                        e.getFieldName(),
                        e.getFieldAttr(),
                        e.getFieldTitle(),
                        e.getFieldGrade(),
                        e.getFieldNotNull(),
                        e.getFieldStatus(),
                        e.getCreateTime(),
                        e.getUpdateTime()
                ).setFormMasterAttr(formMasterRepository.findByFormIdAndFormStatusNot(e.getFormId(), FormStatusEnum.DEL.getCode()))
        ).collect(Collectors.toList());


        return collect;
    }

    @Override
    @Transactional
    public void delField(String fieldId, String openid) {
        FormField result = formFieldRepository.findById(fieldId).get();
        if (result == null) {
            log.error("【删除字段】 字段错误 ");
            throw new FormException(ResultEnum.FIELD_NOT_EXIST);
        }
        checkForm(result.getFormId(), openid);
        result.setFieldStatus(FieldStatusEnum.DEL.getCode());
        formFieldRepository.save(result);
    }

    @Override
    public void delAllField(String formId, String openid) {
        checkForm(formId, openid);
        formFieldRepository.deleteStatusByFormId(formId);
    }

    private FormMaster checkForm(String formId, String openid) {
        FormMaster formMaster = formMasterRepository.findByFormIdAndFormStatusNot(formId,FormStatusEnum.DEL.getCode());
        if (formMaster == null) {
            log.error("【查询字段】 表单错误 formId={}", formId);
            throw new FormException(ResultEnum.FORM_NOT_EXIST);
        }
        if (!formMaster.getOpenid().equals(openid)) {
            log.error("【查询字段】 openid错误 formMaster={},openid={}", formMaster, openid);
            throw new FormException(ResultEnum.PARAM_ERROR);
        }
        return formMaster;
    }

}
