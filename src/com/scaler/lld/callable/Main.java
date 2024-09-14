package com.scaler.lld.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Node in = new Node(1);
        in.left = new Node(2);
        in.right = new Node(3);
        in.left.left = new Node(4);
        ExecutorService es = Executors.newCachedThreadPool();
        System.out.println(es.submit(new TreeSizeCalculator(in,es)).get());

    }
}
