package com.hhb.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 15:33
 * @Description:
 */
public class LockExample6 {

    private static final Logger logger = LoggerFactory.getLogger(LockExample6.class);

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            try {
                reentrantLock.lock();
                TimeUnit.MILLISECONDS.sleep(2000);
                logger.info("wait signal1"); // 等待信号 1
                condition.await(); // 释放锁，然后加入到condition等待队列里面

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("get signal1"); // 获取信号 4
            reentrantLock.unlock();

        }).start();


        new Thread(() -> {
            reentrantLock.lock();
            logger.info("get lock"); // 获取锁 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 从Condition队列中取出来线程放到AQS等待队列里面，只是放到队列一面，并没有被唤醒
            logger.info("send signal"); // 发送信号 3
            reentrantLock.unlock();

        }).start();


    }

}
