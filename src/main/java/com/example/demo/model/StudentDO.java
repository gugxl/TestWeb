package com.example.demo.model;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDO {
    private Long id;
    private String studentNo;
    private String studentName;
    private String phone;
    private String email;
    private String sex;
    private Integer Status;
    private Date gmtCreated;
    private Date gmtModified;
}
