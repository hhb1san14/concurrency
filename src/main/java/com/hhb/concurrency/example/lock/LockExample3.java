package com.hhb.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 14:13
 * @Description: 悲观读取，悲观锁，读取多、写入少，容易造成饥饿
 */
public class LockExample3 {


    private static final Logger logger = LoggerFactory.getLogger(LockExample3.class);

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock wirteLock = lock.writeLock();


    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }

    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data data) {
        wirteLock.lock(); // 只有当没有任何读写锁的时候，才可以进行写操作，保证数据的正确性
        try {
            return map.put(key, data);
        } finally {
            wirteLock.unlock();
        }
    }

    class Data {

    }

}
