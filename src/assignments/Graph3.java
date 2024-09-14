package assignments;
import java.util.*;

public class Graph3 {


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

    public int solve(int A, int[][] B) {
        if(B.length == 0) return 0;
        HashMap<Integer, ArrayList<int[]>> hm = new HashMap<>();
        for(int[] x:B){
            int s = x[0];
            int e = x[1];
            int w = x[2];
            if(hm.containsKey(s)) hm.get(s).add(new int[]{w,e});
            else{
                ArrayList<int[]> al = new ArrayList<>();
                al.add(new int[]{e,w});
                hm.put(s,al);
            }
            if(hm.containsKey(e)) hm.get(e).add(new int[]{s,e});
            else
            {
                ArrayList<int[]> al = new ArrayList<>();
                al.add(new int[]{s,w});
                hm.put(e,al);
            }
        }
        boolean[] v = new boolean[A+1];
        Arrays.fill(v,false);
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2){
                return o2[0] - o1[0];
            }
        });
        v[1] = true;
        for(int[] nei:hm.get(1)) heap.add(nei);
        int tot = 0;
        while(!heap.isEmpty()){
            int[] el = heap.remove();
            int node = el[0];
            int weight = el[1];
            if(!v[node]){
                v[node] = true;
                for(int[] nei:hm.get(node)) heap.add(nei);
                tot  += weight;
            }
        }
        return tot;
    }
    public static void main(String[] args) {
        Graph3 s = new Graph3();
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;

            }
        });
        heap.add(2);
        heap.add(5);
        System.out.println(heap.remove());



    }
}
