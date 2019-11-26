package com.hhb.concurrency.other;

/**
 * @author: huanghongbo
 * @Date: 2019-10-29 17:39
 * @Description:
 */
public class ThreadDemo implements Runnable {


    @Override
    public void run() {
        log();
    }


    public synchronized void log() {
        System.err.println(Thread.currentThread().getName());

    }

}
