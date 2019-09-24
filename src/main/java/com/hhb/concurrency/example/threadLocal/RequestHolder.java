package com.hhb.concurrency.example.threadLocal;


/**
 * @author: huanghongbo
 * @Date: 2019-06-16 08:43
 * @Description:
 */
public class RequestHolder {


    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();


    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long get(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }


}
