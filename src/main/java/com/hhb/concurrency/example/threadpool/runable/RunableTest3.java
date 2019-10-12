package com.hhb.concurrency.example.threadpool.runable;

import java.util.concurrent.CountDownLatch;

/**
 * @author: huanghongbo
 * @Date: 2019-10-12 17:05
 * @Description:
 */
public class RunableTest3 implements Runnable {

    private CountDownLatch downLatch;

    private String name;


    public RunableTest3(CountDownLatch countDownLatch, String name) {
        this.downLatch = countDownLatch;
        this.name = name;
    }


    @Override
    public void run() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.currentThread().setName(name);
        System.err.println(Thread.currentThread().getName() + "  执行完了");
        downLatch.countDown();
    }
}
