package com.john.best.newproject.core;

import lombok.Data;

/**
 * @author john
 * @date 2022/10/23
 */
@Data
public class UnifyResponse {
    private Integer code;
    private String message;
    private String request;

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }
}
