package com.hhb.concurrency.example.syncContainer;

import com.hhb.concurrency.annoations.NotThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-17 13:39
 * @Description:
 */
@NotThreadSafe
public class CollectionsExample3 {

    private final static int clientTotal = 5000;

    private final static int threadTotal = 200;

    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

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

        System.err.println("size: " + map.size());

    }

    private static void update(int count) {
        map.put(count, count);
    }


}