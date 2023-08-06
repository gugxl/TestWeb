package com.example.demo.hadler;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResultVO handleException(Exception e) {
        ErrorCode unknowError = ErrorCode.UNKNOW_ERROR;
        log.error(e.getMessage(), e);
        return ResultVO.buildFail(unknowError.getCode(), unknowError.getMessage());
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @retu
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResultVO handleException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error(errorCode.toString());
        return ResultVO.buildFail(errorCode.getCode(), errorCode.getMessage());
    }

}
