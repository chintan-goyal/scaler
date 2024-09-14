package com.scaler.lld.generics;

import java.util.Iterator;

public  class Node {
    static class Nab implements Iterable{


        @Override
        public Iterator iterator() {
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Object next() {
                    return null;
                }
            };
        }
    }
}

