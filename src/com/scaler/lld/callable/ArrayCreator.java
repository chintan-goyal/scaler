package com.scaler.lld.callable;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ArrayCreator implements Callable<ArrayList<Integer>> {
    private int num;

    public ArrayCreator(int n) {
        this.num = n;
    }

    @Override
    public ArrayList<Integer> call() {
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 1;i<=num;i++) {
//            System.out.println(Thread.currentThread());
            al.add(i);
        }
        return al;
    }
}
