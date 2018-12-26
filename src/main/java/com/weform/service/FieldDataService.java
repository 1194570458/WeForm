package com.weform.service;

import com.weform.vo.FieldDataVO;
import com.weform.vo.FieldMasterVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 16:42
 */
public interface FieldDataService {

    //添加数据
    FieldMasterVO create(FieldMasterVO fieldMasterVO);

    //获取数据
    List<List<FieldDataVO>> list(String formId, Integer page, Integer size, String openid);


}
