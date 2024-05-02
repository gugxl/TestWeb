package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小谷
 * @description
 * @since 2024/4/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportUser {
    private String name;

    public String getUserName() {
        return name;
    }
}
