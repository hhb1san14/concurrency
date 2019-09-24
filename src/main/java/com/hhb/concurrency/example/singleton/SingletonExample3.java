package com.hhb.concurrency.example.singleton;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 17:23
 * @Description: 懒汉模式，线程安全，但是不推荐
 */
public class SingletonExample3 {

    private static SingletonExample3 instance = null;

    private SingletonExample3() {

    }


    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
