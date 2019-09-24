package com.hhb.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author: huanghongbo
 * @Date: 2019-06-18 17:29
 * @Description:
 */
public class FutureExample {

    private static final Logger logger = LoggerFactory.getLogger(FutureExample.class);

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            logger.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new MyCallable());

        logger.info("do other something");

        Thread.sleep(2000);
        logger.info("准备获取结果");
        String result = future.get();
        logger.info("result : {}", result);

    }

}
