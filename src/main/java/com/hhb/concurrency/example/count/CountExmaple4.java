package com.hhb.concurrency.example.count;

import com.hhb.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-13 17:20
 * @Description:
 */
@NotThreadSafe
public class CountExmaple4 {

    private static final Logger logger = LoggerFactory.getLogger(CountExmaple4.class);

    //请求总数
    public static int clientTotal = 5000;

    //同时执行的并发数
    public static int threadTotal = 20;

    public static volatile int count = 0;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        logger.info("count:{}", count);

    }

    public static void add() {
        count++;
        // 1、从主存中取出count
        // 2、执行count++
        // 3、写会主存
    }




}
