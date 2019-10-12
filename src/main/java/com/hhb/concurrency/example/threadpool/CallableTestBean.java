package com.hhb.concurrency.example.threadpool;

/**
 * @author: huanghongbo
 * @Date: 2019-10-12 17:21
 * @Description:
 */
public class CallableTestBean {

    private Integer id;

    private String Name;

    public Integer getId() {
        return id;
    }

    public CallableTestBean setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public CallableTestBean setName(String name) {
        Name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CallableTestBean{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
