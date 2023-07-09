package com.example.demo.model;

public class ResultVO<T> {
    private int code;
    private String errorMsg;
    private T data;

    private ResultVO(int code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static <T> ResultVO<T> buildSuccess(T data){
        return new ResultVO<>(0, null, data);
    }

    public static <T> ResultVO<T> buildFail(int errorCode, String errorMsg){
        return new ResultVO<>(errorCode, errorMsg, null);
    }
}
