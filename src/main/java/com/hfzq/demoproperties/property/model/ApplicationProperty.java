package com.hfzq.demoproperties.property.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 应用属性
 * @Author Zephyr Lin
 * @Date 2022/2/10 10:30
 **/
@Data
@Component
public class ApplicationProperty {

    @Value("${application.name}")
    private String name;

    @Value("${application.version:version default}")
    private String version;

    @Value("${application.randomId}")
    private String randomId;
}
