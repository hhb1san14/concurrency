package com.hhb.concurrency.example.aqs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 10:21
 * @Description:
 */
public class ForkJoinExample extends RecursiveTask<Integer> {


    private static final Logger logger = LoggerFactory.getLogger(ForkJoinExample.class);

    public static final int threadHold = 2;

    private int start;

    private int end;

    public ForkJoinExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小，就计算任务
        boolean canCompute = (end - start) <= threadHold;
        if (canCompute) {
            for (int i = start; i < end; i++) {
                sum += i;

            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务执行
            int middle = (start + end) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(start, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 计算结果
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinExample forkJoinExample = new ForkJoinExample(1, 100);

        Future<Integer> result = forkJoinPool.submit(forkJoinExample);

        try {
            logger.info("result : {}", result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
