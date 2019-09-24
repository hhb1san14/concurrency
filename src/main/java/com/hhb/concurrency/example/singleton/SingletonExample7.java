package com.hhb.concurrency.example.singleton;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 18:52
 * @Description:
 */
public class SingletonExample7 {


    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.SINGLETON.getSingleton();
    }

    private enum Singleton {

        SINGLETON;

        private SingletonExample7 singletonExample7;

        private Singleton() {
            singletonExample7 = new SingletonExample7();
        }

        private SingletonExample7 getSingleton() {
            return singletonExample7;
        }

    }

}
