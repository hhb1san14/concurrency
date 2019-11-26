package com.hhb.concurrency.example.atomic;

import com.hhb.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: huanghongbo
 * @Date: 2019-06-13 17:20
 * @Description:
 */
@ThreadSafe
public class AtomicExmaple6 {


    //请求总数
    public static int clientTotal = 5000;

    //同时执行的并发数
    public static int threadTotal = 20;


    private static AtomicBoolean isHappen = new AtomicBoolean(false);


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
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
    }

    /**
     * 让if里面的代码只执行一次
     */
    private static void test() {
        if (isHappen.compareAndSet(false, true)) {
            System.err.println("============");
        }
    }


}
