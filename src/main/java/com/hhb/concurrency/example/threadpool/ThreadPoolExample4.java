package com.hhb.concurrency.example.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author: huanghongbo
 * @Date: 2019-06-19 14:11
 * @Description:
 */
public class ThreadPoolExample4 {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExample4.class);

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);


        // 延迟多久执行
//        executorService.schedule(() -> {
//            logger.info("-----");
//        }, 3, TimeUnit.SECONDS);


        //延迟1秒，没隔3秒执行一次
//        executorService.scheduleAtFixedRate(() -> {
//            logger.info("=====");
//        }, 1, 3, TimeUnit.SECONDS);


        // 从当前时间开始执行
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                logger.info("++++++");
//            }
//        }, new Date());

        // 从当前时间开始执行,然后间隔5秒定时执行
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("++++++");
            }
        }, new Date(), 5000);

//        executorService.shutdown();

    }

}
