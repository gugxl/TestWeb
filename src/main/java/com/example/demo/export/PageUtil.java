package com.example.demo.export;

/**
 * @author 小谷
 * @description
 * @since 2024/5/2
 */
public class PageUtil {
    public static int totalPage(int total, int pageSize) {
        if (total<0 || pageSize<0){
            return 0;
        }
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }

    public static int getStart(int i, int pageSize) {
        return i;
    }
}
