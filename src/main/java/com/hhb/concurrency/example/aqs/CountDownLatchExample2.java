package com.hhb.concurrency.example.aqs;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 10:52
 * @Description: 等待子任务一段时间，然后就执行主任务,不管子任务是否都执行完
 */
public class CountDownLatchExample2 {


    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS);
        System.err.println("===========");
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.err.println(threadNum);

    }

}
