package com.hhb.concurrency.example.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 15:14
 * @Description:
 */
public class DeadLockExample implements Runnable {

    private int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    private static final Logger logger = LoggerFactory.getLogger(DeadLockExample.class);


    @Override
    public void run() {
        logger.info("flag : {}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    logger.info("-----");
                }
            }

        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    logger.info("++++++");
                }
            }

        }
    }


    public static void main(String[] args) {
        DeadLockExample deadLockExample1 = new DeadLockExample();
        DeadLockExample deadLockExample2 = new DeadLockExample();

        deadLockExample1.flag = 0;
        deadLockExample2.flag = 1;

        new Thread(deadLockExample1).start();
        new Thread(deadLockExample2).start();
    }

}
