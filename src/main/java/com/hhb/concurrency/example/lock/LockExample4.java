package com.hhb.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 14:13
 * @Description:
 */
public class LockExample4 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static int count = 0;

    private final static StampedLock lock = new StampedLock();

    private static final Logger logger = LoggerFactory.getLogger(LockExample4.class);


    class Point {
        private double x, y;

        private final StampedLock sl = new StampedLock();


        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }


        // 乐观读锁案例
        double distanceFromOrigin() { // A read-only method
            long stamp = sl.tryOptimisticRead(); // 获得一个乐观锁
            double currentX = x, currentY = y; // 将两个字段读入本地局部变量
            if (!sl.validate(stamp)) { // 检查发出乐观读锁后同时是否有其他的写锁发生
                stamp = sl.readLock(); //如何没有，再次获取一个悲观锁
                try {
                    currentX = x;// 将两个字段呢读入本地局部变量
                    currentY = y;//将两个字段呢读入本地局部变量
                } finally {
                    sl.unlockRead(stamp);//
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);//
        }


        // 悲观读锁案例
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();// 获取一个悲观锁
            try {
                while (x == 0.0 && y == 0.0) {// 循环，检查当前状态是否符合
                    long ws = sl.tryConvertToWriteLock(stamp);// 将读锁转为写锁
                    if (ws != 0L) {// 确认转为写锁是否成功
                        stamp = ws;// 如果成功，替换票据
                        x = newX;// 进行状态修改
                        y = newY;//进行状态修改
                        break;
                    } else { // 如果不能成功转化为写锁
                        sl.unlockRead(stamp);// 释放锁
                        stamp = sl.writeLock();// 显式的进行写锁，然后再通过循环
                    }
                }
            } finally {
                sl.unlock(stamp);// 释放锁
            }
        }

    }

}
