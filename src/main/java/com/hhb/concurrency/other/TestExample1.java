package com.hhb.concurrency.other;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: huanghongbo
 * @Date: 2019-06-24 14:15
 * @Description:
 */
public class TestExample1 {


    public static void main(String[] args) {


//        // Segment数组，分段锁
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//=========================================================================
//        // 读写分离
//        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
//        copyOnWriteArrayList.get(0);
//        copyOnWriteArrayList.add(1);
//=========================================================================
//        //
//        DelayQueue delayQueue = new DelayQueue();
//
//=========================================================================
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        atomicInteger.compareAndSet(0, 1);
//=========================================================================
//        ReentrantLock reentrantLock = new ReentrantLock();

//=========================================================================
//        Object lock = new Object();
//        Thread A = new Thread(() -> {
//            int sum = 0;
//            for (int i = 0; i < 10; i++) {
//                sum += i;
//            }
//
//            synchronized (lock) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.err.println("sum = " + sum);
//        });
//        A.start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        synchronized (lock) {
//            lock.notify();
//        }

//=========================================================================
        //  LockSupport 可以先解锁，在加锁。即先unpark 后 park 也可以正确执行
//        Thread A = new Thread(() -> {
//            int sum = 0;
//            for (int i = 0; i < 10; i++) {
//                sum += i;
//            }
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            LockSupport.park(); // 后执行
//            System.err.println("sum = " + sum);
//        });
//
//        A.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        LockSupport.unpark(A); // 先于park方法执行
//
//=========================================================================


//        ExecutorService executors = Executors.newCachedThreadPool(); // 最大线程数没有设置上限
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10); // 队列没有设置边界，这样会任务堆积，内存撑爆


        // 自定义线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
//                Runtime.getRuntime().availableProcessors() * 2,
//                60,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(200),
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        Thread t = new Thread(r);
//                        t.setName("order-thread"); // 设置名字
//                        if (t.isDaemon()) { // 判断该线程是不是守护线程
//                            t.setDaemon(false);
//                        }
//
//                        if (Thread.NORM_PRIORITY != t.getPriority()) { // 判断线程的优先级
//                            t.setPriority(Thread.NORM_PRIORITY);
//                        }
//                        return t;
//                    }
//                },
//                new RejectedExecutionHandler() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        System.err.println("拒绝策略： " + r);
//                    }
//                });

        // 源码分析
//        ReentrantLock lock = new ReentrantLock();


        // 源码分析
//        try {
//            CountDownLatch countDownLatch = new CountDownLatch(100);
//            countDownLatch.countDown();
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//         源码分析
//        try {
//            Semaphore semaphore = new Semaphore(10);
//            semaphore.acquire();
//            semaphore.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }
}

