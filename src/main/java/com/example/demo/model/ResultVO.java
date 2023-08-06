package com.example.demo.model;

import com.example.demo.exception.ErrorCode;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
@Data
public class ResultVO<T> {
    private Boolean success;
    private String code;
    private String errorMsg;
    private T data;

    private ResultVO(Boolean success, String code, String errorMsg, T data) {
        this.success = success;
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static <T> ResultVO<T> buildSuccess(T data) {
        return new ResultVO<>(Boolean.TRUE, "success", null, data);
    }

    public static <T> ResultVO<T> buildFail(String errorCode, String errorMsg) {
        log.error(" exec fail [{}], [{}]", errorCode, errorMsg);
        return new ResultVO<>(Boolean.FALSE, errorCode, errorMsg, null);
    }

    public static <T> ResultVO<T> buildFail(ErrorCode errorCode) {
        log.error(" exec fail [{}]", errorCode);
        return new ResultVO<>(Boolean.FALSE, errorCode.getCode(), errorCode.getMessage(), null);
    }
}
