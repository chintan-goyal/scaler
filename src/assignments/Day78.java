package assignments;

import java.util.*;

public class Day78 {


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

    public int maxProfit(int[] A, int[] B) {
        int l = A.length;
        int[][] ip = new int[l][2];
        int sum = 0;
        Queue<Integer> heap = new PriorityQueue<>();

        for (int i = 0;i<l;i++){
            ip[i][0] = A[i];
            ip[i][1] = B[i];
        }
        Arrays.sort(ip,(a,b) -> a[0]-b[0]);
        int time = 0;
        for(int[] x : ip ){
            if(time < x[0]){
                time += 1;
                heap.add(x[1]);
            }
            else{
                heap.add(x[1]);
                heap.remove();
            }
        }
        while(!heap.isEmpty()){
            int mod = (int) (Math.pow(10,9)+7);
            sum = (sum)%mod + (heap.remove())%mod;
            sum = sum%mod;
        }
        return sum;

    }



    public static void main(String[] args) {
        Day78 s = new Day78();
        System.out.println(s.maxProfit(new int[]{1,3,2,3,3}, new int[]{5,6,1,3,9}));



    }
}
