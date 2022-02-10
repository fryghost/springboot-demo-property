package com.hfzq.demoproperties.property.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 用户属性
 * @Author Zephyr Lin
 * @Date 2022/2/10 11:03
 **/
@Data
@Component
public class CarProperty {


    @Value("${car.name}") //从配置文件中取值
    private String name;

    @Value("#{9*3}")  // #{SPEL} Spring表达式
    private int age;

    @Value("红")  // 字面量
    private String color;
}
