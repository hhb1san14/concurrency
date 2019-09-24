package com.hhb.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: huanghongbo
 * @Date: 2019-07-11 09:42
 * @Description:
 */
public class AtomicExampleMySelf1 {

    private static Integer total_client = 5000;


    private static Integer thread_total = 200;

    private static Lock lock = new ReentrantLock();

    private static int count = 0;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(total_client);
        final Semaphore semaphore = new Semaphore(thread_total);

        for (int i = 0; i < total_client; i++) {
            executorService.execute(() -> {
                        try {
                            semaphore.acquire();
                            count();
                            semaphore.release();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        countDownLatch.countDown();
                    }
            );

        }

        countDownLatch.await();

        executorService.shutdown();

        System.err.println(count);
    }


    public static int count() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }

        return count;
    }

}
