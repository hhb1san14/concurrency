package com.hhb.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 14:13
 * @Description:
 */
public class LockExample1 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static int count = 0;

    private static final Logger logger = LoggerFactory.getLogger(LockExample1.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);


        for (int i = 0; i < clientTotal; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executor.shutdown();
        logger.info("count=>" + count);
    }

    private static synchronized void add() {
        count++;
    }


}
