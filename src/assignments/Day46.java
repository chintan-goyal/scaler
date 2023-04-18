package assignments;

import java.util.Arrays;
import java.util.List;

public class Day46 {


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

    public int sqrt(int A) {
        int ans = 0, s = 0, e = A, m = 0;
        if (A <= 1) return A;
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (m == A / m) return m;
            else {
                if (m < A / m) {
                    ans = m;
                    s = m + 1;
                } else e = m - 1;
            }
        }
        return ans;
    }

    public int returnSmall(List<Integer> A, Integer B) {
        int s = 0, l = A.size(), e = l - 1, m, ans = 0;
        if (l == 0) return 0;
        else if (B <= A.get(0)) return 0;
        else if (B > A.get(e)) return l;
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (A.get(m) < B) {
                ans = m;
                s = m + 1;
            } else e = m - 1;
        }
        return ans + 1;
    }

    // DO NOT MODIFY BOTH THE LISTS
    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        double res = 0;
        int m = a.size(), n = b.size();
        if (m == 0) return n % 2 == 0 ? (double) (b.get(n / 2) + b.get(n / 2 - 1)) / 2 : b.get(n / 2);
        else if (n == 0) return m % 2 == 0 ? (double) (a.get(m / 2) + a.get(m / 2 - 1)) / 2 : a.get(m / 2);

        int min = Math.min(a.get(0), b.get(0)), max = Math.max(a.get(m - 1), b.get(n - 1));
        int smalleridx1 = min, smalleridx2;
        int cnt1, cnt2;
        if ((m + n) % 2 == 0) {
            smalleridx1 = (m + n) / 2;
            for (int i = min; i <= max; i++) {
                cnt1 = returnSmall(a, i);
                cnt2 = returnSmall(b, i);
                if (cnt1 + cnt2 > smalleridx1 - 1) {
                    smalleridx1 = i - 1;
                    break;
                }
            }
            smalleridx2 = (m + n) / 2 + 1;
            for (int i = smalleridx1; i <= max; i++) {
                cnt1 = returnSmall(a, i);
                cnt2 = returnSmall(b, i);
                if (cnt1 + cnt2 > smalleridx2 - 1) {
                    smalleridx2 = i - 1;
                    res = (double) (smalleridx1 + smalleridx2) / 2;
                    break;
                }
            }

        } else {
            smalleridx2 = (m + n) / 2 + 1;
            for (int i = smalleridx1 + 1; i <= max; i++) {
                cnt1 = returnSmall(a, i);
                cnt2 = returnSmall(b, i);
                if (cnt1 + cnt2 > smalleridx2 - 1) {
                    return i - 1;
                }
            }
        }
        return res;
    }

    public int getGCD(int A, int B){
        if(B%A == 0) return A;
        return getGCD(B%A, A);
    }

    public long getAthPosition(int A, int B, long N){
        return N/A + N/B - N/((A*B)/getGCD(A,B));
    }


    // public get
    public int solve(int A, int B, int C) {
        int mod = (int) Math.pow(10,9) + 7;
        long s = Math.min(B,C), e = (long) A*s, m = 0, res = 0, pos = 0;
        while(s<=e){
            m = (s+e)/2;
            pos = getAthPosition(B,C,m);
            if(pos < A) s = m+1;
            else if(pos > A) e = m-1;
            else {
                res = m;
                e = m-1;
            }
        }
        return (int) ((res%mod)+mod)%mod;
    }



    public static void main(String[] args) {
        Day46 s = new Day46();

        StringBuilder a = new StringBuilder();
        System.out.println(s.solve(807414236,3788,38141)) ;
        //3058485125968
        System.out.println((long)807414236*3788);
        System.out.println((long)807414236*37);
        System.out.println(Long.MAX_VALUE);
//        System.out.println(s.findMedianSortedArrays(Arrays.asList(-43, -25, -18, -15, -10, 9, 39, 40), Arrays.asList(-2)));
       
    }
}
