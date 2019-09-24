package com.hhb.concurrency.example.commonunsafe;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: huanghongbo
 * @Date: 2019-06-17 10:26
 * @Description:
 */
public class DateFormatExample4 {


    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static int count = 0;

    private final static DateTimeFormatter c = DateTimeFormatter.ofPattern("yyyyMMdd");



    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();


    }

    private static void update() {
        LocalDate dateTime = LocalDate.parse("20190606",c);
        System.err.println(dateTime);
    }


}
