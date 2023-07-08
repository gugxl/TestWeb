package com.example.demo.post.construct;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class TestPostConstruct {
    static {
        System.out.println("方式1. static block");
    }

    public TestPostConstruct() {
        System.out.println("方式2. constructor function");
    }

    @PostConstruct
    public void init() {
        System.out.println("方式3. PostConstruct 执行初始化方法");
    }

}
