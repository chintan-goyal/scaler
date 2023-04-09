package assignments;

import java.util.Arrays;
import java.util.HashMap;

public class Day44 {


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

    public int getMaxMinDiff(int[] A) {
        long res = 0, min = 0, max = 0, mod = 1000000007;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            int maxl = i, minl = A.length - 1 - i;
            res = (res
                    + A[i] * (getPow(2, maxl, (int) mod) - getPow(2, minl, (int) mod)+mod) % mod
            ) % mod;
            System.out.println(res);
        }
        //306568771
//        System.out.println(res % mod);
        return (int) (((res % mod)+mod)%mod);
    }

    public int[] subUnsort(int[] A) {
        int min_idx = 0, max_idx = 0, min_el, max_el;
        //Geting first breaking point
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                min_idx = i - 1;
                break;
            }
        }
        //Geting second breaking point
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                max_idx = i + 1;
                break;
            }
        }
        //System.out.println("Min "+A[min_idx] + " " +min_idx );
        //System.out.println("Max "+A[max_idx] + " " +max_idx );
        if (min_idx == 0 & max_idx == 0) {
            return new int[]{-1};
        }
        int finl_min = min_idx, finl_max = max_idx;
        //Checking the minimum and maximum within range
        for (int i = finl_min; i <= finl_max; i++) {
            if (A[i] < A[min_idx]) min_idx = i;
            if (A[i] > A[max_idx]) {
                max_idx = i;
            }
        }

        //System.out.println("After range Min "+A[min_idx] + " " +min_idx );
        //System.out.println("Max "+A[max_idx] + " " +max_idx );

        for (int i = 0; i < A.length; i++) {
            if (A[i] > A[min_idx]) {
                min_idx = i;
                break;
            }
        }

        for (int i = min_idx; i <= finl_max; i++) {
            if (A[i] > A[max_idx]) {
                max_idx = i;
                break;
            }
        }
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < A[max_idx]) {
                max_idx = i;
                break;
            }
        }

        int[] res = new int[2];
        res[0] = min_idx;
        res[1] = max_idx;
        //System.out.println("Min "+A[min_idx] + " " +min_idx );
        //System.out.println("Max "+A[max_idx] + " " +max_idx );
        return res;
    }

    public int[] countSort(int[] A) {
        int max = A[0], min = A[0];
        int[] res = new int[A.length];
        for (int x : A) {
            max = Math.max(x, max);
            min = Math.min(x, min);
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (hm.get(A[i]) != null) hm.replace(A[i], hm.get(A[i]) + 1);
            else hm.put(A[i], 1);
        }

        int idx = 0;
        for (int i = min; i <= max; i++) {
            int cnt = hm.get(i) != null ? hm.get(i) : 0;
            for (int j = 0; j < cnt; j++) {
                res[idx] = i;
                idx++;
            }
        }
        return res;
    }

    public int[][] solve(int[][] A, int B) {
        int[][] res = new int[B][];
        Arrays.sort(A, (o1, o2) -> {
            long a, b;
            a = (long) (Math.pow(o1[0], 2) + Math.pow(o1[1], 2));
            b = (long) (Math.pow(o2[0], 2) + Math.pow(o2[1], 2));
            return (int) (a - b);
        });
        for (int i = 0; i < B; i++) {
            res[i] = A[i];
        }
        return A;
    }

    public String largestNumber(final int[] A) {
        Integer[] res = new Integer[A.length];
        for(int i = 0;i<A.length;i++) res[i] = A[i];
        Arrays.sort(res,(a,b)->{return a-b;});
        return "";

    }


    public static void main(String[] args) {
        Day44 s = new Day44();
//        StringBu

//        System.out.println(s.getMaxMinDiff(new int[]{7, 8, 6, 4, 6, 7, 3, 10, 2, 3, 8, 1, 10, 4, 7, 1, 7, 3, 7, 2, 9, 8, 10, 3, 1, 3, 4, 8, 6, 10, 3, 3, 9, 10, 8, 4, 7, 2, 3, 10, 4, 2, 10, 5, 8, 9, 5, 6, 1, 4, 7, 2, 1, 7, 4, 3, 1, 7, 2, 6, 6, 5, 8, 7, 6, 7, 10, 4, 8, 5, 6, 3, 6, 5, 8, 5, 5, 4, 1, 8, 9, 7, 9, 9, 5, 4, 2, 5, 10, 3, 1, 7, 9, 10, 3, 7, 7, 5, 10, 6, 1, 5, 9, 8, 2, 8, 3, 8, 3, 3, 7, 2, 1, 7, 2, 6, 10, 5, 10, 1, 10, 2, 8, 8, 2, 2, 6, 10, 8, 8, 7, 8, 4, 7, 6, 7, 4, 10, 5, 9, 2, 3, 10, 4, 10, 1, 9, 9, 6, 1, 10, 7, 4, 9, 6, 7, 2, 2, 6, 10, 9, 5, 9, 2, 1, 4, 1, 5, 5, 5, 5, 8, 7, 4, 2, 8, 6, 10, 7, 3, 2, 8, 9, 6, 8, 5, 2, 9, 6, 10, 8, 6, 4, 9, 9, 4, 2, 9, 10, 7, 5, 4, 4, 4, 9, 7, 1, 5, 9, 9, 9, 10, 8, 8, 7, 5, 4, 1, 4, 1, 10, 3, 6, 5, 1, 6, 10, 5, 7, 10, 3, 3, 5, 8, 8, 6, 5, 9, 2, 3, 9, 10, 4, 7, 9, 1, 3, 2, 1, 6, 2, 2, 1, 9, 6, 1, 7, 5, 7, 3, 6, 9, 7, 3, 9, 5, 8, 3, 5, 1, 7, 3, 10, 10, 1, 9, 2, 4, 2, 2, 1, 4, 5, 1, 4, 10, 2, 10, 7, 10, 4, 4, 9, 1, 6, 7, 7, 5, 1, 1, 5, 7, 3, 7, 8, 6, 7, 10, 9, 8, 3, 9, 3, 10, 10, 7, 1, 3, 8, 7, 2, 4, 3, 2, 6, 10, 10, 2, 5, 10, 2, 1, 8, 6, 9, 8, 1, 5, 9, 1, 5, 3, 10, 7, 2, 1, 5, 3, 3, 3, 1, 6, 6, 3, 10, 1, 3, 9, 4, 9, 1, 5, 1, 10, 2, 10, 7, 3, 6, 5, 5, 10, 10, 4, 7, 1, 6, 1, 3, 10, 5, 4, 6, 2, 8, 5, 4, 2, 5, 7, 10, 5, 3, 3, 7, 5, 2, 3, 9, 9, 10, 3, 9, 9, 9, 7, 9, 4, 9, 4, 4, 4, 9, 1, 5, 8, 7, 9, 10, 1, 7, 9, 8, 10, 1, 4, 4, 4, 8, 4, 3, 7, 6, 3, 7, 6, 9, 8, 10, 7, 1, 5, 2, 1, 5, 9, 8, 1, 9, 7, 3, 5, 8, 10, 4, 10, 3, 9, 4, 1, 2, 8, 9, 10, 2, 6, 5, 10, 3, 6, 8, 5, 10, 10, 5, 6, 10, 4, 6, 8, 1, 9, 2, 10, 10, 8, 9, 3, 6, 4, 5, 10, 1, 3, 1, 2, 10, 7, 3, 2, 3, 1, 8, 4, 2, 2, 10, 1, 6, 7, 8, 8, 5, 1, 7, 5, 8, 5, 9, 6, 9, 3, 7, 1, 7, 7, 5, 7, 3, 9, 10, 7, 1, 8, 1, 2, 1, 2, 4, 8, 8, 3, 7, 5, 6, 3, 1, 3, 10, 1, 10, 10, 5, 6, 2, 1, 4, 8, 9, 9, 7, 1, 5, 7, 8, 7, 1, 10, 8, 6, 10, 8, 9, 6, 4, 4, 9, 4, 8, 10, 4, 8, 9, 8, 5, 2, 10, 1, 10, 9, 9, 6, 9, 5, 4, 8, 2, 4, 9, 1, 10, 8, 10, 10, 4, 3, 5, 4, 8, 2, 3, 3, 1, 3, 2, 8, 6, 2, 8, 5, 2, 8, 2, 2, 2, 8, 1, 5, 1, 9, 6, 2, 7, 7, 3, 2, 10, 7, 5, 9, 1, 9, 2, 1, 3, 3, 10, 8, 6, 7, 5, 7, 4, 8, 10, 8, 5, 10, 2, 8, 1, 7, 1, 9, 6, 4, 10, 5, 2, 6, 5, 2, 6, 6, 5, 10, 9, 4, 9, 6, 3, 3, 3, 8, 1, 4, 5, 7, 4, 7, 4, 4, 5, 5, 4, 10, 8, 3, 6, 9, 10, 1, 3, 5, 8, 7, 6, 8, 2, 4, 4, 4, 9, 6, 2, 1, 9, 8, 7, 4, 6, 1, 9, 1, 5, 2, 2, 4, 6, 10, 4, 5, 2, 6, 1, 9, 4, 6, 7, 6, 10, 10, 1, 8, 7, 4, 8, 7, 2, 6, 1, 7, 6, 1, 9, 2, 3, 3, 7, 10, 2, 1, 5, 3, 8, 5, 1, 4, 3, 9, 1, 4, 8, 1, 1, 4, 5, 10, 3, 8, 5, 3, 6, 3, 5, 5, 4, 9, 7, 1, 9, 10, 3, 3, 4, 2, 9, 4, 5, 3, 3, 5, 6, 2, 8, 6, 8, 2, 7, 10, 9, 2, 4, 4, 4, 8, 10, 9, 7, 8, 1, 5, 9, 5, 9, 2, 7, 9, 6, 3, 2, 10, 10, 7, 1, 7, 5, 10, 10, 1, 9, 10, 4, 2, 5, 9, 10, 7, 8, 8, 4, 8, 2, 3, 3, 2, 6, 1, 10, 1, 5, 1, 2, 4, 8, 5, 2, 2, 4, 1, 4, 3, 2, 8, 6, 7, 6, 5, 3, 3, 2, 8, 3}));

        StringBuilder a = new StringBuilder();
//        System.out.println(a.length()                       );
//        s.subUnsort(new int[]{1, 3, 3, 5, 7, 9, 11, 11, 9, 7, 9, 11, 16, 17, 20, 20});
//        s.countSort(new int[]{6, 3, 3, 6, 7, 8, 7, 3, 7});
//        System.out.println(s.makeUnique(new int[]{1,2,2,3,4,4,5}));
//        System.out.println(s.findRank("DTNGJPURFHYEW"));
        System.out.println( 75% 7);
        //6


    }
}
