package com.hhb.concurrency.example.threadpool.runable;

import java.util.concurrent.CountDownLatch;

/**
 * @author: huanghongbo
 * @Date: 2019-10-12 17:05
 * @Description:
 */
public class RunableTest2 implements Runnable {

    private CountDownLatch downLatch;

    private String name;


    public RunableTest2(CountDownLatch countDownLatch, String name) {
        this.downLatch = countDownLatch;
        this.name = name;
    }


    @Override
    public void run() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.currentThread().setName(name);
        System.err.println(Thread.currentThread().getName() + "  执行完了");
        downLatch.countDown();
    }
}
