package com.example.demo.export;

import com.example.demo.model.ExportUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author 小谷
 * @description
 * @since 2024/4/30
 */
@Slf4j
@Component
public class ExportQueue {
    private static final int MAX_CAPACITY = 10; // 容量
    private LinkedList<ExportUser> queue; // 用户队列

    public ExportQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * 排队队列添加
     * @param sysUser
     * @return
     */
    public synchronized LinkedList<ExportUser> add(ExportUser sysUser) {
        while  (queue.size() >= MAX_CAPACITY) {
            log.info("当前排队人已满，请等待");
            try {
                wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

        queue.add(sysUser);
        log.info("目前导出队列排队人数：" + queue.size());
        notifyAll();
        return queue;
    }

    /**
     * 获取排队队列下一个人
     * @return
     */
    public synchronized ExportUser getNextSysUser(){
        while (queue.isEmpty()) {
            log.info("当前没有用户排队，请等待");
            try {
                wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        ExportUser sysUser = queue.remove();
        notifyAll(); //唤醒
        return sysUser;
    }
}
