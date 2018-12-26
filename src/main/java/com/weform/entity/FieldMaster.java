package com.weform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weform.enums.DetailStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/12 16:47
 */
@Data
@Entity
public class FieldMaster {

    @Id
    private String masterId;

    //表单id
    private String formId;

    //状态 默认0 未标记
    private Integer detailStatus = DetailStatusEnum.UNTAN.getCode();

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;


}
