package com.hhb.concurrency.other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: huanghongbo
 * @Date: 2019-10-29 17:38
 * @Description:
 */
public class TestExample2 {


    public static void main(String[] args) throws InterruptedException {

        ThreadDemo test1 = new ThreadDemo();
        ThreadDemo test2 = new ThreadDemo();
        ThreadDemo test3 = new ThreadDemo();
        ThreadDemo test4 = new ThreadDemo();
        ThreadDemo test5 = new ThreadDemo();
        Thread thread1 = new Thread(test1);
        Thread thread2 = new Thread(test2);
        Thread thread3 = new Thread(test3);
        Thread thread4 = new Thread(test4);
        Thread thread5 = new Thread(test5);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


        Lock lock = new ReentrantLock();
        lock.tryLock(1, TimeUnit.SECONDS);

    }




}
