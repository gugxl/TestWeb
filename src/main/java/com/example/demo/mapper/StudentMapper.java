package com.example.demo.mapper;

import com.example.demo.model.StudentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 根据输入的学生信息进行条件检索
     * 1. 当只输入用户名时， 使用用户名进行模糊检索；
     * 2. 当只输入邮箱时， 使用性别进行完全匹配
     * 3. 当用户名和性别都存在时， 用这两个条件进行查询匹配的用
     * @param studentDO
     * @return
     */
    List<StudentDO> selectByStudentSelective(StudentDO studentDO);


}
