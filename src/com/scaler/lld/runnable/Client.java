package com.scaler.lld.runnable;

public class Client {
    public static void main(String[] args){
        System.out.println("I am the main class");
        new ScalerThread(new Adder()).start();
        new ScalerThread(new Subtractor()).start();

    }
}
