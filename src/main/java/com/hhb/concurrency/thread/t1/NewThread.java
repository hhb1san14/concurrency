package com.hhb.concurrency.thread.t1;

/**
 * @author: huanghongbo
 * @Date: 2019-09-24 15:53
 * @Description:
 */
public class NewThread implements Runnable {

    @Override
    public void run() {
        System.err.println("线程运行了。。。。");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new NewThread()); // 创建线程,并指定线程业务
        thread.start(); // 启动线程

    }

}
