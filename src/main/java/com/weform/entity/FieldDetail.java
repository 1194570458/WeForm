package com.weform.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: Kason
 * @Date: 2018/12/12 16:51
 */

@Data
@Entity
public class FieldDetail {

    @Id
    private String detailId;

    //字段id
    private String fieldId;

    //主表id
    private String masterId;

    //数据
    private String formData;

    public FieldDetail(String detailId, String fieldId, String masterId, String formData) {
        this.detailId = detailId;
        this.fieldId = fieldId;
        this.masterId = masterId;
        this.formData = formData;
    }

    public FieldDetail() {
    }
}
