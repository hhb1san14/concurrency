package com.hhb.concurrency.example.threadpool;

import com.hhb.concurrency.example.threadpool.runable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 14:11
 * @Description:
 */
public class ThreadPoolExample5 {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExample5.class);

    private final static CountDownLatch countDownLatch = new CountDownLatch(3);


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

//            executorService.submit()
//            executorService.execute();
        RunableTest1 runableTest1 = new RunableTest1(countDownLatch,"第一个方法");
        RunableTest2 runableTest2 = new RunableTest2(countDownLatch,"第二个方法");
        RunableTest3 runableTest3 = new RunableTest3(countDownLatch,"第三个方法");
        executorService.submit(runableTest1);
        executorService.execute(runableTest2);
        executorService.execute(runableTest3);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("主线程等待其他线程执行完了");
        executorService.shutdown();
    }


}
