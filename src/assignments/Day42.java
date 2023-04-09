package assignments;

import java.util.Arrays;

public class Day42 {

    public int makeUnique(int[] A){
        Arrays.sort(A);
        int prev_el= A[0], res = 0;
        for(int i = 1;i<A.length;i++){
            if(A[i]<=prev_el){
                prev_el += 1;
                res += prev_el - A[i];
            }
            else prev_el = A[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Day42 s = new Day42();
        System.out.println(s.makeUnique(new int[]{1,2,2,3,4,4,5}));
//        System.out.println(s.findRank("DTNGJPURFHYEW"));


    }
}
