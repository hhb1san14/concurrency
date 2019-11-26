package com.hhb.concurrency.other.myself;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author: huanghongbo
 * @Date: 2019-10-26 13:55
 * @Description:
 */
public class TailUtil {


    public static void main(String[] args) throws IOException {
        Queue<String> queue = getQueue("/Users/baiwang/Downloads/Exam/test-files/large.txt", 3);
//        Queue<String> queue = getQueue("/Users/baiwang/Downloads/Exam/test-files/empty.txt", 3);
//        Queue<String> queue = getQueue("/
//        Users/baiwang/Downloads/Exam/test-files/one-line.txt", 3);

        for (String q : queue) {
            System.err.println(q);
        }
    }


    /**
     * 将数据放入队列中，始终让队列种的数据保持为n个。
     *
     * @param fileName
     * @param n
     * @return
     * @throws IOException
     */
    public static Queue<String> getQueue(String fileName, int n) throws IOException {
        Queue<String> queue = new ArrayDeque<>();
        int count = 0; // 记录队列中的数量
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        String line = "";
        while ((line = br.readLine()) != null) {
            if (count < n) { //队列中的数量小于目标数量
                queue.offer(line);
                count++;
            } else {
                queue.poll(); // 删除第一个
                queue.offer(line); // 放入最新的
            }
        }
        return queue;
    }

}
