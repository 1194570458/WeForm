package com.weform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Kason
 * @Date: 2018/12/24 9:29
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectUrlConfig {

    private String form;

}
