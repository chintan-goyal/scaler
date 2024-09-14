package assignments;

import java.util.*;

public class Day77 {


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

    public ArrayList<Integer> kPlaceApart(ArrayList<Integer> A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (B == 0) return A;
        Queue<Integer> heap = new PriorityQueue<>();
        int l = A.size();
        for(int x:A){
            if (heap.size()>B) ans.add(heap.remove());
            heap.add(x);
        }
        while(!heap.isEmpty()) ans.add(heap.remove());
        return ans;
    }

    public int[] largestAthElement(int A, int[] B){
        int l = B.length;
        int[] res = new int[l];
        Queue<Integer> heap = new PriorityQueue<Integer>();
        for (int i = 0; i < l; i++) {
            heap.add(B[i]);
            if(heap.size() < A) res[i] = -1;
            else if (heap.size() == A) res[i] = heap.peek();
            else{
                heap.remove();
                res[i] = heap.peek();
            }
        }
        return res;
    }

    public int[] runningMedian(int[] A){
        int l = A.length;
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int[] res = new int[l];
        res[0] = A[0];
        maxHeap.add(A[0]);

        for(int i = 1; i<l;i++){
            if(maxHeap.size() > minHeap.size()){
                maxHeap.add(A[i]);
                minHeap.add(maxHeap.remove());
            }
            else{
                maxHeap.add(A[i]);
                minHeap.add(maxHeap.remove());
                maxHeap.add(minHeap.remove());
            }
            res[i] = maxHeap.peek();
        }

        return res;
    }

    public int solution2(int[] A){
        int o1 = 0,o2 = 0,o3 = 0;
        int l = A.length;
        o1 = helper(A,A[0]+A[l-1],1,1,l-2);
        o2 = helper(A,A[0]+A[1],1,2,l-1);
        o3 = helper(A,A[l-1]+A[l-2],1,0,l-3);
        return Math.max(o3,Math.max(o1,o2));

    }

    private int helper(int[] A, int sum, int count, int st, int ed) {
        int o1 = 0,o2 = 0,o3 = 0;
        boolean flag = false;
        if(ed-st + 1< 2) return count;
        if(A[st] + A[ed] == sum){
            flag = true;
            o1 = helper(A,sum, count +1, st+1, ed-1);
        }
        if(A[st] + A[st + 1] == sum){
            flag = true;
            o2 = helper(A, sum, count+1, st+2, ed);
        }
        if(A[ed]+A[ed-1] == sum){
            flag = true;
            o3 = helper(A, sum, count+1, st, ed -2);
        }
        if(!flag) return count;
        else  return Math.max(o3,Math.max(o1,o2));

    }


    public static void main(String[] args) {
        Day77 s = new Day77();
        int[] a = s.largestAthElement(4, new int[]{1,2,3,4,5,6});
//        for(int x:a) System.out.println(x);
        System.out.println(s.solution2(new int[]{1,1,2,3,1,2,2,1,1,2}));

    }
}
