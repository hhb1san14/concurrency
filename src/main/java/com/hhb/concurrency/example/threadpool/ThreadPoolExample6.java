package com.hhb.concurrency.example.threadpool;

import com.hhb.concurrency.example.threadpool.callable.CallableTest1;
import com.hhb.concurrency.example.threadpool.callable.CallableTest2;
import com.hhb.concurrency.example.threadpool.callable.CallableTest3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 14:11
 * @Description:
 */
public class ThreadPoolExample6 {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExample6.class);

    private final static CountDownLatch countDownLatch = new CountDownLatch(3);


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();

//            executorService.submit()
//            executorService.execute();
        CallableTest1 callableTest1 = new CallableTest1(countDownLatch);
//        CallableTest2 callableTest2 = new CallableTest2(countDownLatch);
//        CallableTest3 callableTest3 = new CallableTest3(countDownLatch);
        Future<CallableTestBean> future = executorService.submit(callableTest1);
        System.err.println("开始获取值");
        System.err.println(future.get(2, TimeUnit.SECONDS));

        System.err.println("主线程等待其他线程执行完了");
        executorService.shutdown();
    }


}
