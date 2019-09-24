package com.hhb.concurrency.example.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huanghongbo
 * @Date: 2019-06-15 19:31
 * @Description:不可变对象
 */
public class ImmutableExample1 {

    private static Map<Integer,Integer> map = new HashMap<>();

    static {
        map.put(1,2);
        map.put(2,3);
        map = Collections.unmodifiableMap(map);
//        map.put(2,4);
    }

    public static void main(String[] args) {
        System.err.println(map);
    }


}
