package com.scaler.lld.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class TreeSizeCalculator implements Callable<Integer> {
    Node n;
    ExecutorService ex;

    public TreeSizeCalculator(Node node, ExecutorService executorService) {
        this.n = node;
        this.ex = executorService;
    }

    public Integer getSize() throws ExecutionException, InterruptedException {
        if (n == null) return 0;
        return 1 + ex.submit(new TreeSizeCalculator(this.n.left, this.ex)).get() + ex.submit(new TreeSizeCalculator(this.n.right, this.ex)).get();
    }

    @Override
    public Integer call() throws Exception {
        return getSize();
    }
}
