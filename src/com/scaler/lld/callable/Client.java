package com.scaler.lld.callable;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //Callable only happens with ThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ArrayCreator res = new ArrayCreator(n);
        Future<ArrayList<Integer>> ans = executorService.submit(res);
        List<Integer> fin = ans.get();
        System.out.println(fin);
        executorService.shutdown();



    }

}
