package com.example.demo.model.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@Document(indexName = "gugu_test")
public class User {
    private String name;
    private int age;
    private String[] like;
}
