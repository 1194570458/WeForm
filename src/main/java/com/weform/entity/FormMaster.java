package com.weform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weform.enums.FormStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/11 15:57
 */
@Data
@DynamicUpdate
@Entity
public class FormMaster {

    @Id
    private String formId;

    /**
     * 表单名字
     */
    @NotEmpty(message = "名称必填")
    private String formName;

    /**
     * 用户openid
     */
    @NotEmpty(message = "openid必填")
    private String openid ;

    @Transient
    private Long dataAmount;

    /**
     * 表单状态
     * -1已删除,0未开始,1收集中,2已停止
     */
    private Integer formStatus = FormStatusEnum.NOMAL.getCode();

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime ;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;


}
