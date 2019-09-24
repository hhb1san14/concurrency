package com.hhb.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huanghongbo
 * @Date: 2019-06-14 16:56
 * @Description:
 */
public class SyncExmaple2 {


    // 修饰代码块
    public static void test1(int j) {
        synchronized (SyncExmaple2.class) {
            for (int i = 0; i < 10; i++) {
                System.err.println("test"+j+" - " + i);
            }
        }
    }


    //修饰一个方法
    public static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            System.err.println("test2 - " + i);
        }
    }


    public static void main(String[] args) {
        SyncExmaple2 syncExmaple1 = new SyncExmaple2();
        SyncExmaple2 syncExmaple2 = new SyncExmaple2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            syncExmaple1.test1(1);
        });

        executorService.execute(()->{
            syncExmaple2.test1(2);
        });


    }

}
