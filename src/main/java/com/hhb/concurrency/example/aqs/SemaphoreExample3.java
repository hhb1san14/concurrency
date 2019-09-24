package com.hhb.concurrency.example.aqs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 10:52
 * @Description: 对并发访问的控制，线程拿到许可才可以执行
 */
public class SemaphoreExample3 {


    private static final Logger logger = LoggerFactory.getLogger(SemaphoreExample3.class);

    private static int clientCount = 200;

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();


        final Semaphore semaphore = new Semaphore(threadCount);

        for (int i = 0; i < clientCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // semaphore.tryAcquire() 尝试是否可以拿到许可，如果拿不到许可，直接不执行
                    // tryAcquire(long timeout, TimeUnit unit) 在一定的时间内尝试获取许可，如果拿不到许可，直接不执行
                    // tryAcquire(int permits)  一次性获取多个许可，如果拿不到许可，直接不执行
                    // tryAcquire(int permits, long timeout, TimeUnit unit)，在一定时间内，获取多个许可，如果拿不到许可，直接不执行
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) { // 尝试是否可以拿到许可，如果拿不到许可，直接不执行
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }
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
