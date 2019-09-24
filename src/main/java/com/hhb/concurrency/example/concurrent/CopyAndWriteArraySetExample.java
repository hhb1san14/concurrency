package com.hhb.concurrency.example.concurrent;

import com.hhb.concurrency.annoations.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: huanghongbo
 * @Date: 2019-06-17 13:39
 * @Description:
 */
@ThreadSafe
public class CopyAndWriteArraySetExample {

    private final static int clientTotal = 5000;

    private final static int threadTotal = 200;

    private static Set<Integer> set = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch downLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downLatch.countDown();

            });
        }
        downLatch.await();
        executorService.shutdown();
        System.err.println("size: " + set.size());

    }

    private static void update(int count) {
        set.add(count);
    }


}
