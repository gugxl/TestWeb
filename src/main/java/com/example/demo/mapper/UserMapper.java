package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user ")
    List<User> list();

    @Select("select * from user where name like #{name}")
    List<User> findByUsername(String name);

    @Select("select * from user where id = #{id}")
    User getOne(Long id);

    @Delete("delete from user where id = #{id}")
    int delete(Long id);
}
