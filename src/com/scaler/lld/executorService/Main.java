package com.scaler.lld.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("HI");
        //Creating dynamic threads
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 1;i<=100;i++){
            executorService.submit(new NumberPrinter(i));
        }
        executorService.shutdown();
    }

}
