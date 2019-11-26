package com.hhb.concurrency.other.myself;

/**
 * @author: huanghongbo
 * @Date: 2019-10-25 16:30
 * @Description:
 */
public class Node {

    private Node next;

    private Integer a;

    public Node getNext() {
        return next;
    }

    public Node setNext(Node next) {
        this.next = next;
        return this;
    }

    public Integer getA() {
        return a;
    }

    public Node setA(Integer a) {
        this.a = a;
        return this;
    }


    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", a=" + a +
                '}';
    }
}
