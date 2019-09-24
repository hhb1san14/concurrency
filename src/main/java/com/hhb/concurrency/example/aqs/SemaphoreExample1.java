package com.hhb.concurrency.example.aqs;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 10:52
 * @Description: 对并发访问的控制，线程拿到许可才可以执行
 */
public class SemaphoreExample1 {


    private static int clientCount = 200;

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();


        final Semaphore semaphore = new Semaphore(threadCount);

        for (int i = 0; i < clientCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 拿到一个许可，没拿到许可就等待，拿到一个许可才可以执行
                    test(threadNum);
                    semaphore.release(); // 释放一个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        System.err.println("113123");
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        System.err.println(threadNum);
        Thread.sleep(1000);

    }

}
