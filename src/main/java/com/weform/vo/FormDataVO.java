package com.weform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/25 10:59
 */
@Data
public class FormDataVO {

    private String formId;

    @JsonProperty(value = "data")
    private List<List<FieldDataVO>> fieldData;

}
