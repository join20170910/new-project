package com.john.best.newproject.exception.http;

/**
 * @author john
 * @date 2022/10/23
 */
public class ForbiddenException extends HttpException{
    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode =403;
    }
}
