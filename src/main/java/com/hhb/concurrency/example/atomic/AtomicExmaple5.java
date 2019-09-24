package com.hhb.concurrency.example.atomic;

import com.hhb.concurrency.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: huanghongbo
 * @Date: 2019-06-13 17:20
 * @Description:
 */
@ThreadSafe
public class AtomicExmaple5 {

    /**
     * 作用于某一个类的一个被volatile修饰的非static的属性
     */
    private static AtomicIntegerFieldUpdater<AtomicExmaple5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExmaple5.class, "count");

    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private static AtomicExmaple5 atomicExmaple5 = new AtomicExmaple5();

    public static void main(String[] args) {
        if (updater.compareAndSet(atomicExmaple5, 100, 120)) {
            System.err.println("success1   " + atomicExmaple5.getCount());
        }
        if (updater.compareAndSet(atomicExmaple5, 100, 120)) {
            System.err.println("success2   " + atomicExmaple5.getCount());
        } else {
            System.err.println("failed1   " + atomicExmaple5.getCount());
        }
    }


}
