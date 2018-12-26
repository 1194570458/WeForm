package com.weform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weform.enums.DetailStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/12 16:46
 */
@Data
public class FieldMasterVO {

    //主数据表id
    private String masterId;

    private String openid;

    //表单id
    @NotEmpty(message = "表单id必填")
    private String formId;

    private Integer detailStatus = DetailStatusEnum.UNTAN.getCode();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JsonProperty("data")
    private String data;

}
