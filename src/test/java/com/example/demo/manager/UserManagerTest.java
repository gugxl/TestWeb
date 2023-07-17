package com.example.demo.manager;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserManagerTest {
    @Autowired
    private UserManager userManager;

    @Test
    void getAll() {
        List<User> all = userManager.getAll();
        System.out.println(all.size());
    }
}