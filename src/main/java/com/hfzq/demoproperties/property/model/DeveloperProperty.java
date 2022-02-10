package com.hfzq.demoproperties.property.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 开发人员属性
 * @Author Zephyr Lin
 * @Date 2022/2/10 10:36
 **/
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {

    private String name;

    private String website;

    private String email;

    private String uuid;

    private Map<String,Object> map;

    private List<Object> lists;

    private boolean flag;
}
