package com.weform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Kason
 * @Date: 2018/12/25 10:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDataVO {

    private String fieldId;

    private String formData;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

}
