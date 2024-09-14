package com.scaler.lld.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Car implements Comparable<Car > {
    private int speed;
    private int power;
    public Car(int speed, int power){
        this.speed = speed;
        this.power = power;
    }
    public <T extends ArrayList<Integer> >int sum(T a, T b){
        return a.get(0) - b.get(0);
    }
    @Override
    public String toString() {
        return "[S=" + this.speed + ", P=" + this.power + "]";
    }

    @Override
    public int compareTo(Car o) {
        return speed - o.speed;
    }
}
