package com.example.demo.controller;

import com.example.demo.manager.TestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/demo/test")
public class TestController {

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
}
