package com.john.best.newproject.exception.http;

import java.io.InputStream;

/**
 * @author john
 * @date 2022/10/23
 */
public class HttpException extends RuntimeException{
    protected Integer code;
    protected Integer httpStatusCode = 500;

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
