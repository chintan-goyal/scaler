package assignments;

import java.util.*;

// Definition for singly-linked list.
  class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) {
          val = x; next = null;
      }
  }

public class Day76 {


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

    public int connectedRopes(int[] A){
        Queue<Integer> heap = new PriorityQueue<>();
        int cost = 0;
        for(int x:A){
            heap.add(x);
        }
        while (heap.size()!= 0){
            if(heap.size() == 1){
                cost += heap.remove();
            }
            else{
                int temp_cost = heap.remove() + heap.remove();
                cost += temp_cost;
                heap.add(temp_cost);
            }
        }
        return cost;

    }
    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode res = new ListNode(-1);
        ListNode head = res;
        Queue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < a.size(); i++) {
            heap.add(new int[]{a.get(i).val,i});
        }

        while (!heap.isEmpty()){
            int[] el = heap.remove();
            head.next = new ListNode(el[0]);
            head = head.next;

            if(a.get(el[1]).next != null){
                a.set(el[1],a.get(el[1]).next);
                heap.add(new int[]{a.get(el[1]).val,el[1]});
            }
        }
        return res.next;
    }




    public static void main(String[] args) {
        Day76 s = new Day76();
        StringBuilder a = new StringBuilder();
        Queue<int[]> p = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        int[] A = new int[]{5,3,6,2,1};
        for (int j = 0; j<A.length; j++) {
            p.add(new int[]{j,A[j]});
        }

    }
}
