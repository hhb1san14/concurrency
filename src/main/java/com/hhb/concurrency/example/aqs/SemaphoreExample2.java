package com.hhb.concurrency.example.aqs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 10:52
 * @Description: 对并发访问的控制，线程拿到多个许可才可以执行
 */
public class SemaphoreExample2 {

    private static final Logger logger = LoggerFactory.getLogger(SemaphoreExample2.class);

    private static int clientCount = 20;

    private static int threadCount = 3;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();


        final Semaphore semaphore = new Semaphore(threadCount);

        for (int i = 0; i < clientCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3); // 拿到多个许可
                    test(threadNum);
                    semaphore.release(2); // 释放多个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        System.err.println("113123");
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        logger.info("{}", threadNum);
        Thread.sleep(1000);

    }

}
