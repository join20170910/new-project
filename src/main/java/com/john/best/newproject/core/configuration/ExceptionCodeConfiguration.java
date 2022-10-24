package com.john.best.newproject.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author john
 * 错误配置类
 * @date 2022/10/23
 */
@ConfigurationProperties(prefix = "john")
@PropertySource(value = "classpath:config/exception-code.properties")
@Component
public class ExceptionCodeConfiguration {
    private Map<Integer,String> codes = new HashMap<>();

    public String getMessage(Integer code){
        final String message = codes.get(code);
        return message;
    }
    public void setCodes(Map<Integer,String> codes){
        this.codes = codes;
    }


}
