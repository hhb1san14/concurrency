package com.hhb.concurrency.example.singleton;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 17:23
 * @Description: 懒汉模式，使用时在创建，不加锁，不安全
 */
public class SingletonExample1 {

    private static SingletonExample1 instance = null;

    private SingletonExample1() {

    }


    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }

}
