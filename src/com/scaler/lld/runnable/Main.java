package com.scaler.lld.runnable;

public class Main {
    public static void main(String[] args) {
        System.out.println("HI");
        Concurrency c = new Concurrency();
        for(int i = 0; i<100;i++){
            Thread t1 = new Thread(c);
        }
    }
}
