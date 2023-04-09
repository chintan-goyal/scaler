package assignments;

import java.util.ArrayList;

public class Day41 {

    // inversion
    public int solve(ArrayList<Integer> A) {
        return 0;
    }

    public void joinSorted(int[] A, int s, int m1, int m2, int e) {
        int l = e - s + 1, l1 = m1 - s + 1, l2 = e - m2 + 1, idx = 0, idx1 = s, idx2 = m2;
        int[] help = new int[l];
        while (idx1 <= m1 & idx2 <= e) {
            if (A[idx1] <= A[idx2]) {
                help[idx] = A[idx1];
                idx1++;
            } else {
                help[idx] = A[idx2];
                idx2++;
            }
            idx++;
        }
        while (idx1 <= m1) {
            help[idx] = A[idx1];
            idx1++;
            idx++;
        }
        while (idx2 <= e) {
            help[idx] = A[idx2];
            idx2++;
            idx++;
        }
        for (int i = 0; i < l1; i++) {
            A[i + s] = help[i];
        }
        for (int i = 0; i < l2; i++) {
            A[m2 + i] = help[i + l1];
        }
    }

    public void mergeSortHelper(int[] A, int s, int e) {
        int l = e - s + 1;
        int m1 = s + l / 2 - 1, m2 = s + l / 2;
        if (l > 1 & e > s) {
            mergeSortHelper(A, s, m1);
            mergeSortHelper(A, m2, e);
            joinSorted(A, s, m1, m2, e);
        }
    }

    public void mergeSort(int[] A) {
        mergeSortHelper(A, 0, A.length - 1);

    }

    public int joinSortedInv(int[] A, int s, int m1, int m2, int e) {
        int l = e - s + 1, l1 = m1 - s + 1, l2 = e - m2 + 1, idx = 0, idx1 = s, idx2 = m2;
        int[] help = new int[l];
        long res = 0;
        int mod = (int) Math.pow(10, 9) + 7;
        while (idx1 <= m1 & idx2 <= e) {
            if (A[idx1] <= A[idx2]) {
                help[idx] = A[idx1];
                idx1++;
            } else {
                help[idx] = A[idx2];
                res = (res + m1 - idx1 + 1 + mod) % mod;
                idx2++;
            }
            idx++;
        }
        while (idx1 <= m1) {
            help[idx] = A[idx1];
//            res += l2;
            idx1++;
            idx++;
        }
        while (idx2 <= e) {
            help[idx] = A[idx2];
            idx2++;
            idx++;
        }
        for (int i = 0; i < l1; i++) {
            A[i + s] = help[i];
        }
        for (int i = 0; i < l2; i++) {
            A[m2 + i] = help[i + l1];
        }

        return (int) res;
    }

    public int mergeSortHelperInv(int[] A, int s, int e) {
        int l = e - s + 1;

        int m1 = s + l / 2 - 1, m2 = s + l / 2;
        if (l > 1 & e > s) {
            int r1 = mergeSortHelperInv(A, s, m1);
            int r2 = mergeSortHelperInv(A, m2, e);
            int r3 = joinSortedInv(A, s, m1, m2, e);
//          return ((r1 + r2)+mod%mod) + r3;
            int mod = (int) Math.pow(10, 9) + 7;
            return (((r1 + r2) % mod + mod) % mod + r3 + mod) % mod;

        }
        return 0;
    }

    public int mergeSortInv(int[] A) {
        return mergeSortHelperInv(A, 0, A.length - 1);

    }

    public static void main(String[] args) {
        Day41 s = new Day41();
        int[] x = new int[]{5, 1, 2, 1};
//        int[] x = new int[]{6, 12, 10, 17, 10, 22, 9, 19, 21, 31, 26, 8};
        //        Code for merge sort
        System.out.println(s.mergeSortInv(x));
        //        s.mergeSort(x);
//        Solution.printArray(x);
    }
}
