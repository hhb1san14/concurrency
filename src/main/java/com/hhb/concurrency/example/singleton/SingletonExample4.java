package com.hhb.concurrency.example.singleton;


/**
 * @author: huanghongbo
 * @Date: 2019-06-15 17:33
 * @Description: 双重同步锁单例模式，但是线程不安全
 */
public class SingletonExample4 {


    private SingletonExample4() {

    }

    /**
     * 创建对象的过程：
     * 1、 memory = allocate()  分配对象内存空间
     * 2、ctorInstance()  初始化对象
     * 3、instance = memory 设置instance指向刚才的分配的内存
     * <p>
     * 不安全的原因：
     * cpu和jvm优化，发生了指令重排序
     * <p>
     * 上面的过程变成了
     * 1、 memory = allocate()  分配对象内存空间
     * 2、instance = memory 设置instance指向刚才的分配的内存
     * 3、ctorInstance()  初始化对象
     * <p>
     * <p>
     * 假设现在有两个线程 A、B
     * B线程执行到了4，但是执行到上面创建对象的第二步，还没有初始化时
     * A线程指向到了1步骤，就会直接返回
     */


    private static SingletonExample4 instance;


    public static SingletonExample4 getInstance() {
        if (instance == null) {  // 1
            synchronized (SingletonExample4.class) { // 2
                if (instance == null) { // 3
                    instance = new SingletonExample4(); // 4
                }
            }
        }
        return instance; //5
    }

}
