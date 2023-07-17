package com.example.demo.mapper;

import com.example.demo.model.StudentDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void selectByStudentSelective() {
        StudentDO studentDO = new StudentDO();
        studentDO.setStudentName("明");
        System.out.println("只有名字时的查询");
        List<StudentDO> studentDOS = studentMapper.selectByStudentSelective(studentDO);
        studentDOS.forEach(System.out::println);
    }
}