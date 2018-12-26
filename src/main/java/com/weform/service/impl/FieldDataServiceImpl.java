package com.weform.service.impl;

import com.weform.converter.Data2FieldDataVO;
import com.weform.entity.FieldDetail;
import com.weform.entity.FieldMaster;
import com.weform.entity.FormMaster;
import com.weform.enums.DetailStatusEnum;
import com.weform.enums.FieldStatusEnum;
import com.weform.enums.FormStatusEnum;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.repository.FieldDetailRepository;
import com.weform.repository.FieldMasterRepository;
import com.weform.repository.FormMasterRepository;
import com.weform.service.FieldDataService;
import com.weform.utils.KeyUtil;
import com.weform.vo.FieldDataVO;
import com.weform.vo.FieldMasterVO;
import com.weform.vo.FormDataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Kason
 * @Date: 2018/12/12 19:04
 */
@Service
@Slf4j
public class FieldDataServiceImpl implements FieldDataService {

    @Autowired
    private FieldMasterRepository fieldMasterRepository;

    @Autowired
    private FieldDetailRepository fieldDetailRepository;

    @Autowired
    private FormMasterRepository formMasterRepository;

    @Override
    @Transactional
    public FieldMasterVO create(FieldMasterVO fieldMasterVO) {
        FormMaster formMaster = formMasterRepository.findByFormIdAndFormStatusNot(fieldMasterVO.getFormId(), FormStatusEnum.DEL.getCode());

        if (!formMaster.getFormStatus().equals(FormStatusEnum.START.getCode())) {
            log.error("【添加数据】表单不可编辑 formMaster={}", formMaster);
            throw new FormException(ResultEnum.FORM_NOT_START);
        }

        if (formMaster == null) {
            log.error("【添加数据】表单不存在 fieldMasterVO={}", fieldMasterVO);
            throw new FormException(ResultEnum.FORM_NOT_EXIST);
        }
        //准备主表
        String primaryKey = KeyUtil.createPrimaryKey();
        fieldMasterVO.setMasterId(primaryKey);
        FieldMaster fieldMaster = new FieldMaster();
        BeanUtils.copyProperties(fieldMasterVO, fieldMaster);

        fieldMasterRepository.save(fieldMaster);

        //添加数据

        List<FieldDetail> fieldDetailList = Data2FieldDataVO.data2FieldDataVOList(fieldMasterVO).stream().map(
                e -> new FieldDetail(KeyUtil.createPrimaryKey(), e.getFieldId(), primaryKey, e.getFormData())
        ).collect(Collectors.toList());


        fieldDetailRepository.saveAll(fieldDetailList);
        return fieldMasterVO;
    }

    @Override
    public List<List<FieldDataVO>> list(String formId,Integer page, Integer size,String openid) {
        //校验openid formid
        FormMaster formMaster = formMasterRepository.findByFormIdAndFormStatusNot(formId, FormStatusEnum.DEL.getCode());
        if(formMaster==null){
            throw new FormException(ResultEnum.FORM_NOT_EXIST);
        }
        //查询form里的master
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FieldMaster> fieldMasterPage = fieldMasterRepository.findByFormIdAndDetailStatusNot(formId, FieldStatusEnum.DEL.getCode(), pageRequest);


        //查询master所有数据
        FormDataVO formDataVO = new FormDataVO();
        ArrayList<List<FieldDataVO>> lists = new ArrayList<>();
        for (FieldMaster fieldMaster : fieldMasterPage.getContent()) {
            List<FieldDetail> masterId = fieldDetailRepository.findByMasterId(fieldMaster.getMasterId());
            List<FieldDataVO> fieldDataVOS = masterId.stream().map(e -> new FieldDataVO(
                    e.getFieldId(),
                    e.getFormData(),
                    fieldMaster.getCreateTime()
            )).collect(Collectors.toList());
            lists.add(fieldDataVOS);
        }
        formDataVO.setFieldData(lists);

        return lists;
    }

}
