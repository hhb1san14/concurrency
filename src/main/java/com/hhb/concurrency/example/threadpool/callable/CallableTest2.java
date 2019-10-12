package com.hhb.concurrency.example.threadpool.callable;

import com.hhb.concurrency.example.threadpool.CallableTestBean;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author: huanghongbo
 * @Date: 2019-10-12 17:20
 * @Description:
 */
public class CallableTest2 implements Callable<CallableTestBean> {

    private CountDownLatch countDownLatch;

    public CallableTest2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public CallableTestBean call() throws Exception {
        System.err.println("第二个线程正在执行");
        Thread.sleep(3000);
        CallableTestBean callableTestBean = new CallableTestBean();
        callableTestBean.setId(2);
        callableTestBean.setName("第二个方法");
        System.err.println("第二个线程正在执行完了");
//        countDownLatch.countDown();
        return callableTestBean;
    }

}
