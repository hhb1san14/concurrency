package com.hhb.concurrency.atguigu.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: huanghongbo
 * @Date: 2019-11-27 20:23
 * @Description:
 */
class Demo {

    //    private volatile int number = 0;  //1
    private int number = 0;   //2


    public void add() {
        this.number = 100;
    }

    public int getNumber() {
        return number;
    }

    public Demo setNumber(int number) {
        this.number = number;
        return this;
    }

}

/**
 * 两种运行结果
 * 当执行1时候，线程1修改完后，立刻输出  内存可见
 * 当执行2时候，线程1修改完后，程序死循环
 */
public class test {
    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(() -> {
            System.err.println(Thread.currentThread().getName() + "开始 执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            demo.add();
            System.err.println(Thread.currentThread().getName() + "已经执行完,当前number = " + demo.getNumber());
        }, "线程1").start();

        while (demo.getNumber() == 0) {

        }
        System.err.println("内存可见");

    }
}


