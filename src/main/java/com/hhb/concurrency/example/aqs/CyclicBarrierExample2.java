package com.hhb.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 11:45
 * @Description:
 */
public class CyclicBarrierExample2 {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExample2.class);

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int treadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    rece(treadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.err.println("=====");
    }


    private static void rece(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | BrokenBarrierException e) {

        }
        logger.info("{} continue", threadNum);
    }

}
