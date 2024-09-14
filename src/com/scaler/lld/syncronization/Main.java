package com.scaler.lld.syncronization;

import java.util.Iterator;


public class Main {
    static class Node implements Iterable<Integer> {
        private int data;
        private Node next;

        public Node(int data, Node next1) {
            this.data = data;
            this.next = next1;
        }

        @Override
        public Iterator iterator() {
                return new Iterator<Integer>() {
                private Node curr = Node.this;
                public boolean hasNext() {
                    return curr != null;
                }

                @Override
                public Integer next() {
                    Integer temp = curr.data;
                    curr = curr.next;
                    return temp;
                }
            };
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hi");
        int x = 0;
        Object ab = new Node(2, null);
        Node c = ab instanceof Node ? ((Node) ab) : null;
        System.out.println(c);
        for (int xyz : c) {

        }

        //        Counter c = new Counter();
//        Thread t1 = new Thread(new Adder(c));
//        Thread t2 = new Thread(new Subtractor(c));
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(c.val);
//        List.of(1,2,3,4,5,6).stream().anyMatch(el -> el%2 == 0 ).forEach(eleq -> System.out.println(eleq));


    }
}

class Logger {
    private static Logger ins;

    private Logger() {
    }

    public static Logger getInstance() {
        return ins == null ? new Logger() : ins;
    }
}

class Adder implements Runnable {
    public Counter x;

    public Adder(Counter x) {
        this.x = x;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) this.x.val += 1;
        new Object();
    }


}

class Subtractor implements Runnable {
    public Counter x;

    public Subtractor(Counter x) {
        this.x = x;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) this.x.val -= 1;
    }


}

class Counter {
    int val = 0;
}

