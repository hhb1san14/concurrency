package com.hhb.concurrency.example.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-07-12 11:59
 * @Description:
 */
public class CountExample5 {


    private final static int client_total = 5000;

    private final static int thread_total = 200;


    private volatile static long count = 0;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(client_total);
        final Semaphore semaphore = new Semaphore(thread_total);

        for (int i = 0; i < client_total; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.err.println(count);

    }

    public static void add() {
        count++;
    }


}
