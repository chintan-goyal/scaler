package assignments;

import java.util.HashMap;
import java.util.HashSet;

public class Day49 {


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

    public int longestConsecutive(final int[] A) {
        HashSet<Integer> hs = new HashSet<>();
        int res = 0, cnt = 0;
        for (int x : A) hs.add(x);
        for (int i : hs) {
            if (!hs.contains(i - 1)) {
                int itr = i;
                while (hs.contains(itr)) {
                    cnt += 1;
                    itr += 1;
                }
                res = Math.max(cnt, res);
            }
        }
        return res;
    }

    public int maximumDistance(int[] A) {
        int res = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (hm.get(A[i]) == null) hm.put(A[i], i);
            else {
                res = Math.min(i - hm.get(A[i]), res);
                hm.replace(A[i], i);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Day49 s = new Day49();
        StringBuilder a = new StringBuilder();
        HashSet<Integer> hs = new HashSet<>();
        hs.add(2);


    }
}
