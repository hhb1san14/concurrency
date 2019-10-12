package com.hhb.concurrency.other;

/**
 * @author: huanghongbo
 * @Date: 2019-10-11 10:32
 * @Description: 三个线程，轮流打印出 A、B、C
 */
public class NotifyDemo2 {


    public static void main(String[] args) {
        ABC abc = new ABC();
        A a = new A(abc);
        B b = new B(abc);
        C c = new C(abc);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);

        t1.start();
        t2.start();
        t3.start();


    }

    static class ABC {
        int i = 1;
    }

    static class A implements Runnable {

        private ABC abc;

        public A(ABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            synchronized (abc) {
                while (true) {
                    if (abc.i % 3 != 1) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.err.println("A");
                    abc.i = 2;
                    abc.notifyAll();
                }
            }

        }
    }


    static class B implements Runnable {

        private ABC abc;

        public B(ABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            synchronized (abc) {
                while (true) {
                    if (abc.i % 3 != 2) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.err.println("B");
                    abc.i = 3;
                    abc.notifyAll();
                }
            }
        }
    }

    static class C implements Runnable {

        private ABC abc;

        public C(ABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            synchronized (abc) {
                while (true) {
                    if (abc.i % 3 != 0) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.err.println("C");
                    abc.i = 1;
                    abc.notifyAll();
                }
            }
        }
    }
}
