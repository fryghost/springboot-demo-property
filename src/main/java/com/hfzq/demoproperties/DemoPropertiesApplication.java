package com.hfzq.demoproperties;

import com.hfzq.demoproperties.property.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description : 启动类
 * @author Zephyr Lin
 * @created 2022/2/10 9:52
 * @return
 */
@SpringBootApplication
public class DemoPropertiesApplication {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoPropertiesApplication.class, args);
    }

}
