package com.hhb.concurrency.example.syncContainer;

import com.hhb.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @author: huanghongbo
 * @Date: 2019-06-17 13:39
 * @Description: 一个线程删除、一个线程get，这样出现数组越界
 */
@NotThreadSafe
public class VectorExample2 {


    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }


            /**
             *
             */

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) { // 线程获取到i = 9 时候，删除
                    vector.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {// 线程获取到i = 9 时候，挂起来，执行上买呢的线程。然后就会数组越界
                    vector.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }
    }


}
