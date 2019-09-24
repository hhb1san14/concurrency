package com.hhb.concurrency.example.atomic;

import com.hhb.concurrency.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: huanghongbo
 * @Date: 2019-06-13 17:20
 * @Description:
 */
@ThreadSafe
public class AtomicExmaple4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {

        count.compareAndSet(0, 2); // 判断当前count是不是0，如果是0，就更新为2，否则不做更新，下面同理
        count.compareAndSet(0, 2);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        System.err.println(count.get());
    }


}
