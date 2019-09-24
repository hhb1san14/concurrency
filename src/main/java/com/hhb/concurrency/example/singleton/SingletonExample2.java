package com.hhb.concurrency.example.singleton;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 17:23
 * @Description: 饿汉模式，类在加载是被创建，如果里面不做什么其他操作，为线程安全
 * 但是如果该类一直不被调用，浪费空间
 */
public class SingletonExample2 {

    private static SingletonExample2 instance = new SingletonExample2();

    private SingletonExample2() {

    }


    public static SingletonExample2 getInstance() {
        return instance;
    }

}
