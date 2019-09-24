package com.hhb.concurrency.example.syncContainer;

import com.hhb.concurrency.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author: huanghongbo
 * @Date: 2019-06-17 13:39
 * @Description: 一个线程删除、一个线程get，这样出现数组越界
 */
@NotThreadSafe
public class VectorExample3 {


    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
//        test1(vector); //java.util.ConcurrentModificationException
        //test2(vector); //java.util.ConcurrentModificationException
        test3(vector); // ok
        System.err.println(vector);
    }

    private static void test1(Vector<Integer> v) {
        for (Integer i : v) {
            if (i == 3) {
                v.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i == 3) {
                v.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i) == 3) {
                v.remove(i);
            }

        }
    }

}
