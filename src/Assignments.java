import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public void printArrayList(ArrayList<Integer> a) {
        for (int x : a) {
            System.out.println(x);
        }
    }

    public ArrayList<Integer> getprefsum(ArrayList<Integer> input) {
        ArrayList<Integer> res = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i);
            res.add(sum);
        }
        return res;
    }

    public int getsum(ArrayList<Integer> pref, int start_idx, int end_idx) {
        if (start_idx > end_idx) {
            return 0;
        }
        if (start_idx == 0) {
            return pref.get(end_idx);
        } else {
            return pref.get(end_idx) - pref.get(start_idx - 1);
        }
    }

    public int maxProfit(final int[] A) {
        int l = A.length;
        int[] maxar = new int[l];
        if (l < 2) {
            return 0;
        }
        int maxel = A[l - 1];
        maxar[l - 1] = A[l - 1];
        int profit = 0;

        //get max selling profit from back
        for (int i = l - 2; i >= 0; i--) {
            if (A[i] > maxel) {
                maxel = A[i];
            }
            maxar[i] = A[i];
        }

        //get max profit
        for (int j = 0; j < l - 1; j++) {
            if (maxar[j] - A[j] > profit) {
                profit = maxar[j] - A[j];
            }
        }
        return profit;
    }


//    public int pickboth(int[] A, int B) {
//        int l = A.length;
//        int max_sum = Integer.MIN_VALUE;
//        int[] pref = new int[l];
//        int sum = 0;
//        for (int i = 0; i < l; i++) {
//            pref[i] = sum + A[i];
//            sum += A[i];
//        }
//
//        for (int j = 0; j <= B; j++) {
//            int lsum = getsum(pref, 0, j - 1);
//            int rsum = getsum(pref, l - B + j, l - 1);
//            int totsum = lsum + rsum;
//            if (totsum > max_sum) {
//                max_sum = totsum;
//            }
//        }
//        return max_sum;
//
//    }

    public int goodsubarray(ArrayList<Integer> A, int B) {
        int l = A.size();
        ArrayList<Integer> pref = getprefsum(A);
        int count = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                int d = j - i + 1;
                if (d % 2 == 0) {
                    if (getsum(pref, i, j) < B) {
                        count += 1;
                    }
                } else {
                    if (getsum(pref, i, j) > B) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }


    public void rotatemat (ArrayList<ArrayList<Integer>> A) {
        int l = A.size();
        //transposing the matrix
        for(int i = 0;i<l;i++){
            for(int j = 0;j<=i ;j++) {
                int temp = A.get(i).get(j);
                A.get(i).set(j, A.get(j).get(i));
                A.get(j).set(i, temp);
            }
        }
    //reversing each row of matrix
    for(int row = 0;row<l;row++){
        for(int col = 0;col<l/2;col++){
            int temp = A.get(row).get(col);
            A.get(row).set(col, A.get(row).get(l-1-col));
            A.get(row).set(l-1-col, temp);

        }
    }

    }

}


class Assignments {
    public static void main(String[] args) {
        Solution x = new Solution();
        System.out.println((-40) % 7);
        ArrayList<ArrayList<Integer>> input_2d = new ArrayList(Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(4,5,6),Arrays.asList(7,8,9)));
        x.rotatemat(input_2d);

//        System.out.println(x.goodsubarray(new ArrayList(Arrays.asList(1, 2, 3, 4, 5)), 4));

//        System.out.println(x.pickboth(new int[]{5,-2,3,1,2},3));


//        new Solution().maxProfit(new int[]{1,2,3,4,5});


    }
}
