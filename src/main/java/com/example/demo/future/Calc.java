package com.example.demo.future;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class Calc {
    public static Integer calc(Integer para) {
        try {
            //模拟一个长时间的执行
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    @SneakyThrows
    public static void main(String[] args) {
        final CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
                .thenAccept(System.out::println);
    }
}
