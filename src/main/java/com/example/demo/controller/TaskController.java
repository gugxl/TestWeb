package com.example.demo.controller;

import com.example.demo.annotation.MyOperation;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.ResultVO;
import com.example.demo.schedule.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TaskController {
    @Autowired
    private ScheduleTask scheduleTask;

    @MyOperation("用户修改")
    @GetMapping("/updateCron")
    public String updateCron(String cron) {
        log.info("new cron :{}", cron);
        scheduleTask.setCron(cron);
        return "ok";
    }

    @GetMapping("/updateTimer")
    public String updateCron(Long timer) {
        log.info("new timer :{}", timer);
        scheduleTask.setTimer(timer);
        return "ok";
    }

    @MyOperation("异常测试")
    @GetMapping("/testException")
    public ResultVO<String> updateCron(boolean throwException) {
        if (throwException){
            throw new BusinessException(ErrorCode.ILL_ARRGUMENT);
        }
        return ResultVO.buildSuccess("SUCCESS");
    }

}
