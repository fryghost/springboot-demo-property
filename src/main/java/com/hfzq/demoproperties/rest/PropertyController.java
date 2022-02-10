package com.hfzq.demoproperties.rest;

import cn.hutool.core.lang.Dict;
import com.hfzq.demoproperties.property.model.ApplicationProperty;
import com.hfzq.demoproperties.property.model.DeveloperProperty;
import com.hfzq.demoproperties.property.model.CarProperty;
import com.hfzq.demoproperties.property.utils.YamlCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试属性读取
 * @Author Zephyr Lin
 * @Date 2022/2/10 10:28
 **/
@RestController
public class PropertyController {

    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;
    private final CarProperty carProperty;

    @Autowired
    public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty, CarProperty carProperty) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
        this.carProperty = carProperty;
    }

    @GetMapping("/property")
    public Dict index() {
        return Dict.create().set("applicationProperty", applicationProperty).set("developerProperty", developerProperty).set("userProperty", carProperty);
    }

    @GetMapping("/getProperty")
    public Dict getProperty(@RequestParam("propertyName") String propertyName){
        return Dict.create().set("property", YamlCfg.getStr(propertyName));
    }
}
