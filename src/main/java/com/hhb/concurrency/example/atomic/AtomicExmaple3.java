package com.hhb.concurrency.example.atomic;

import com.hhb.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: huanghongbo
 * @Date: 2019-06-13 17:20
 * @Description:
 */
@ThreadSafe
public class AtomicExmaple3 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExmaple3.class);

    //请求总数
    public static int clientTotal = 5000;

    //同时执行的并发数
    public static int threadTotal = 20;

    public static LongAdder count = new LongAdder();

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
        count.increment();
    }


}
