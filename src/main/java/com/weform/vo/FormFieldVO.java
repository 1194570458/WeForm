package com.weform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weform.entity.FormField;
import com.weform.entity.FormMaster;
import com.weform.enums.FieldNotNullEnum;
import com.weform.enums.FieldStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/27 10:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldVO {

    public FormFieldVO setFormMasterAttr( FormMaster formMaster) {
        this.formName=formMaster.getFormName();
        this.formStatus=formMaster.getFormStatus();
        return this;
    }

    public FormFieldVO(String fieldId, String formId, String fieldType, String fieldClass, String fieldName, String fieldAttr, String fieldTitle, Integer fieldGrade, Integer fieldNotNull, Integer fieldStatus, Date createTime, Date updateTime) {
        this.fieldId = fieldId;
        this.formId = formId;
        this.fieldType = fieldType;
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
        this.fieldAttr = fieldAttr;
        this.fieldTitle = fieldTitle;
        this.fieldGrade = fieldGrade;
        this.fieldNotNull = fieldNotNull;
        this.fieldStatus = fieldStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private String fieldId;

    private String formId;

    private String formName;

    private Integer formStatus;

    private String fieldType;

    private String fieldClass;

    private String fieldName;

    private String fieldAttr;

    private String fieldTitle;

    private Integer fieldGrade = 0;

    private Integer fieldNotNull = FieldNotNullEnum.NULL.getCode();

    private Integer fieldStatus = FieldStatusEnum.USED.getCode();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

}
