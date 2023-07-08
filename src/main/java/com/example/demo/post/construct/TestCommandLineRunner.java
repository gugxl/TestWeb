package com.example.demo.post.construct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("方式5. 实现CommandLineRunner类执行run方法");
    }
}
