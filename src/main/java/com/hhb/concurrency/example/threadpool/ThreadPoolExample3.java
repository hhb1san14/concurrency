package com.hhb.concurrency.example.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 14:11
 * @Description:
 */
public class ThreadPoolExample3 {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExample3.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                logger.info("task:{}", index);
            });
        }
        executorService.shutdown();

    }

}
