package com.hhb.concurrency.example.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 18:52
 * @Description: 自己写的单例，不知道对错
 */
public class SingletonExample8 {


    private static AtomicReference<SingletonExample8> atomicReference = new AtomicReference<>();

    private SingletonExample8() {

    }

    public static SingletonExample8 getInstance() {
        atomicReference.compareAndSet(null, new SingletonExample8());
        return atomicReference.get();

    }

    public static void main(String[] args) {

    }

}
