package assignments;

import java.util.*;

public class DayGraph2 {


    public static int getPow(int A, int B, int P) {
        long res = 1;
        if (B == 0) return 1;
        if (A == 1) return 1;
        if (B == 1) return A % P;
        long half = getPow(A, B / 2, P);
        long ans = getMultiModuled(half, half, P);
        res = B % 2 == 0 ? ans : getMultiModuled(ans, A, P);
        return (int) res;

    }

    public static int getMultiModuled(long A, long B, long C) {
        return (int) ((A * B + C) % C);
    }

    public int solve(int A, int[][] B, int C, int D) {
        int inf = Integer.MAX_VALUE/2;
        if(B.length == 0) return 0;
        int src = C;
        int dst = D;
        if(src == dst) return 0;
        HashMap<Integer, ArrayList<int[]>> hm = new HashMap<>();
        for(int[] x:B){
            if(x[2] == 1){
                int s = x[0];
                int e = x[1];
                int w = x[2];
                hm.putIfAbsent(s,new ArrayList<int[]>());
                hm.putIfAbsent(e,new ArrayList<int[]>());
                hm.get(s).add(new int[]{e,w});
                hm.get(e).add(new int[]{s,w});
            }
            else{
                int s = x[0];
                int e = A;
                int w = 1;
                hm.putIfAbsent(s,new ArrayList<int[]>());
                hm.putIfAbsent(e,new ArrayList<int[]>());
                hm.get(s).add(new int[]{e,w});
                hm.get(e).add(new int[]{s,w});
                s = A;
                e = x[1];
                w = 1;
                hm.putIfAbsent(s,new ArrayList<int[]>());
                hm.putIfAbsent(e,new ArrayList<int[]>());
                hm.get(s).add(new int[]{e,w});
                hm.get(e).add(new int[]{s,w});
            }
        }
        boolean[] v = new boolean[A+1];
        Arrays.fill(v,false);
        Queue<int[]> q = new LinkedList<>();
        v[src] = true;
        if(!hm.containsKey(src)) return -1;
        for(int[] nei:hm.get(src)) q.add(nei);
        int finaldist = inf;
        while(!q.isEmpty()){
            int[] el = q.remove();
            int node = el[0];
            int weight = el[1];
            if(node == dst) return weight;
            if(!v[node] ){
                v[node] = true;
                for(int[] nei:hm.get(node)){
                    if(nei[0] != dst && !v[nei[0]]){
                        q.add(new int[]{nei[0],weight+nei[1]});
                    }
                    else if (nei[0] == dst){
                        return weight+nei[1];
                    }

                }
            }

        }
        return -1;

    }


    public static void main(String[] args) {
        DayGraph2 s = new DayGraph2();
//        System.out.println(s.solve(
//                6,
//                new int[][]{{2,5,1},{1,3,1},{0,5,2},{0,2,2},{1,4,1},{0,1,1}},
//                3,2
//        ));
        PriorityQueue<Integer> q = new PriorityQueue<>(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
       for (int i = 0; i < 10; i++) {
        
       }
        while(!q.isEmpty()){
            System.out.println(q.remove());
        }
//        HashMap


    }
}
