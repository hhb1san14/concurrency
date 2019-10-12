package com.hhb.concurrency.example.sync;

/**
 * @author: huanghongbo
 * @Date: 2019-10-10 13:17
 * @Description:
 */
public class SyncExample3 {


    public static void main(String[] args) {
        String s1 = "a";
        String s2 = new String("a");
        System.err.println(s1 == s2);
        s2.intern();
        System.err.println(s1 == s2);



    }


}
