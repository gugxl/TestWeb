package com.example.demo.controller;

import com.example.demo.manager.TestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * 异步测试
 */
@Controller
@RequestMapping(value = "/demo/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestManager testManager;

    @RequestMapping(method = RequestMethod.GET, value = "/async")
    public String testAsync() {
        return testManager.testAsync();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sync")
    public String testSync() {
        return testManager.testSync();
    }

    @GetMapping("/hello")
    public Callable<String> hello() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");

        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " 进入call方法");
                String say = testManager.sayHello();
                logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;
            }
        };
        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;
    }

    @GetMapping("/hello2")
    public WebAsyncTask<String> hello2() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");
        // 3s钟没返回，则认为超时
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {

            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " 进入call方法");
                String say = testManager.sayHello();
                logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;

            }
        });
        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");

        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName() + " 执行完毕");
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {

            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " onTimeout");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException("调用超时");
            }
        });

        return webAsyncTask;
    }

}
