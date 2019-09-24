package com.hhb.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 11:45
 * @Description:
 */
public class CyclicBarrierExample1 {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExample1.class);

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
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }


    private static void rece(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        logger.info("{} is ready", threadNum);
        cyclicBarrier.await();
        logger.info("{} continue", threadNum);
    }

}
