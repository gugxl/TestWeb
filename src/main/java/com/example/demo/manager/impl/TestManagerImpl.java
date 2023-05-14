package com.example.demo.manager.impl;

import com.example.demo.manager.TestManager;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TestManagerImpl implements TestManager {
    @SneakyThrows
    @Override

    public String testAsync() {
        Thread.sleep(1000L);
        return "testAsync";
    }

    @Override
    @SneakyThrows
    public String testSync() {
        Thread.sleep(1000L);
        return "testAsync";
    }
}
