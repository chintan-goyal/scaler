package assignments;

import util.Solution;

import java.util.Arrays;
import java.util.List;

public class Day48 {


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

    public int[] subarrWithDiff(int[] A, int B) {
        int l = A.length, res_length = 0;
        if (l == 1) {
            if (A[0] == B) return new int[]{A[0]};
            else return new int[]{-1};
        }
        long[] pf_sum = new long[l];
        long sum = 0;

        for (int i = 0; i < l; i++) {
            sum += A[i];
            pf_sum[i] = sum;
            if (sum == B) {
                res_length = i + 1;
                int[] res = new int[res_length];
                for (int j = 0; j < res_length; j++) res[j] = A[j];
                return res;
            }
        }

        int left = 1, right = 1;
        while (left <= right + 1 & right < l) {
            if (pf_sum[right] - pf_sum[left - 1] > B) left += 1;
            else if (pf_sum[right] - pf_sum[left - 1] < B) right += 1;
            else {
                res_length = right - left + 1;
                int[] res = new int[res_length];
                for (int j = left; j <= right; j++) res[j - left] = A[j];
                return res;
            }
        }
        return new int[]{-1};
    }

    public int pairsWithSum(int[] A, int B) {
        int l = A.length, left = 0, right = l - 1, mod = (int) Math.pow(10, 9) + 7;
        int left_dups = 0, right_dups = 0;
        long res = 0, sum = 0;
        if (l < 2) return 0;
        while (left < right) {
            sum = A[left] + A[right];
            if (sum > B) right -= 1;
            else if (sum < B) left += 1;
            else {
                // System.out.println(left + " "+right);
                left_dups = 1;
                right_dups = 1;
                int new_left = left, new_right = right;
                new_left += 1;
                while (new_left <= right) {
                    if (A[new_left] == A[new_left - 1]) {
                        left_dups += 1;
                        new_left += 1;
                    } else break;
                }

                if(A[new_left-1] == (double)B/2 ){
                        res  = res%mod + (((long)(left_dups%mod)*(left_dups-1)%mod)/2)%mod;
                        break;
                }
//                left = new_left;
                new_right -= 1;
                while (new_right > left) {
                    if (A[new_right] == A[new_right + 1]) {
                        right_dups += 1;
                        new_right -= 1;
                    } else break;
                }
                res = res%mod + (long) left_dups%mod * right_dups%mod;
//                if(new_left-1 == new_right+1 & A[left] == A[right] ) res -= 1;
                left = new_left;
                right = new_right;
            }
        }
        return (int) ((res % mod) + mod) % mod;
    }


    public static void main(String[] args) {
        Day48 s = new Day48();
        StringBuilder a = new StringBuilder();
        System.out.println(s.pairsWithSum(new int[]{1,1,1},2)); // 3
        System.out.println(s.pairsWithSum(new int[]{1,1,1,2,3,3,3},4)); // 9
        System.out.println(s.pairsWithSum(new int[]{6,6,7},13)); //2
        System.out.println(s.pairsWithSum(new int[]{1,4,4,6},8)); // 1
        System.out.println(s.pairsWithSum(new int[]{1,4,4,4},5)); // 3
        System.out.println(s.pairsWithSum(new int[]{2, 2, 3, 4, 4, 5, 6, 7, 10},8));

    }
}
