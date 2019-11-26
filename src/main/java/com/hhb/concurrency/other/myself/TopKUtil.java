package com.hhb.concurrency.other.myself;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: huanghongbo
 * @Date: 2019-10-28 09:56
 * @Description:
 */
public class TopKUtil {

    public static void main(String[] args) {

        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 100000000; i++) {
            if (queue.size() < 10) {
                queue.offer(i);
            } else {
                if (queue.peek() > i) {
                    queue.poll();
                    queue.offer(i);
                }
            }
        }
        for (Integer i : queue) {
            System.err.println(i);
        }
    }

}
