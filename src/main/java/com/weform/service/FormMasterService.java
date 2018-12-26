package com.weform.service;

import com.weform.entity.FormMaster;
import org.springframework.data.domain.Page;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:01
 */
public interface FormMasterService {


    Page<FormMaster> findListNotEqualsStatus(String openid, Integer page,Integer size);

    //添加表单
    FormMaster save(FormMaster formMaster);

    //修改表单状态
    void changeSatus(FormMaster formMaster);

    void delForm(FormMaster formMaster);

}
