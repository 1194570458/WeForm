package com.weform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weform.enums.FieldNotNullEnum;
import com.weform.enums.FieldStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/11 17:56
 */
@Data
@Entity
@DynamicUpdate
public class FormField {

    @Id
    private String fieldId;

    @NotEmpty(message = "表单id必填")
    private String formId;

    @NotEmpty(message = "类型必填")
    private String fieldType;

    @NotEmpty(message = "类名必填")
    private String fieldClass;

    @NotEmpty(message = "字段名必填")
    private String fieldName;

    @NotEmpty(message = "属性必填")
    private String fieldAttr;

    @NotEmpty(message = "标题必填")
    private String fieldTitle;

    private Integer fieldGrade = 0;

    private Integer fieldNotNull = FieldNotNullEnum.NULL.getCode();

    private Integer fieldStatus = FieldStatusEnum.USED.getCode();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;


}
