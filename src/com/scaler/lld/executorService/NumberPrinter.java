package com.scaler.lld.executorService;

public class NumberPrinter implements Runnable {
    private int num;

    public NumberPrinter(int n) {
        this.num = n;
    }

    @Override
    public void run() {
        System.out.println(this.num + " thread num " + Thread.currentThread());

    }
}
