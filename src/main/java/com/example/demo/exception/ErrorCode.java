package com.example.demo.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum ErrorCode {
    UNKNOW_ERROR("-1","未知异常"),
    SYSTE_ERROR("1001","系统异常"),
    NO_AUTH("3001","没有权限"),
    ILL_ARRGUMENT("3002","非法参数"),

    ;
    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
