package com.example.demo.controller;

import com.example.demo.export.impl.ExportImpl;
import com.example.demo.model.ExportUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小谷
 * @description
 * @since 2024/4/30
 */
@RestController
@RequestMapping("/export")
@Slf4j
public class ExportController {

    @Autowired
    private ExportImpl export;

    @GetMapping("/exportFile")
    public void exportFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                ExportUser exportUser = new ExportUser(thread.getName());
                try {
                    export.export(exportUser);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
