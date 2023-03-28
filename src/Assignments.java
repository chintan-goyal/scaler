import java.util.*;

class LargestNumberComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        String ab = a.toString() + b.toString();
        String ba = b.toString() + a.toString();
        if (Double.parseDouble(ab) > Double.parseDouble(ba)) {
            return -1;
        } else if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }
}

class Solution {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public void printArrayList(ArrayList<Integer> a) {
        for (int x : a) {
            System.out.println(x);
        }
    }

    public void printArray(int[] A) {
        System.out.println("Printing the list");
        for (int x : A
        ) {
            System.out.print(x + " ");
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

    public int[] getprefsum(int[] input) {
        int[] res = new int[input.length];
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            res[i] = sum;
        }
        return res;
    }

    public int getsum(int[] pref, int start_idx, int end_idx) {
        if (start_idx > end_idx) {
            return 0;
        }
        if (start_idx == 0) {
            return pref[end_idx];
        } else {
            return pref[end_idx] - pref[start_idx - 1];
        }
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

    public int pickboth(int[] A, int B) {
        int l = A.length;
        int max_sum = Integer.MIN_VALUE;
        int[] pref = new int[l];
        int sum = 0;
        for (int i = 0; i < l; i++) {
            pref[i] = sum + A[i];
            sum += A[i];
        }

        for (int j = 0; j <= B; j++) {
            int lsum = getsum(pref, 0, j - 1);
            int rsum = getsum(pref, l - B + j, l - 1);
            int totsum = lsum + rsum;
            if (totsum > max_sum) {
                max_sum = totsum;
            }
        }
        return max_sum;

    }

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

    public void rotatemat(ArrayList<ArrayList<Integer>> A) {
        int l = A.size();
        //transposing the matrix
        for (int i = 0; i < l; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = A.get(i).get(j);
                A.get(i).set(j, A.get(j).get(i));
                A.get(j).set(i, temp);
            }
        }
        //reversing each row of matrix
        for (int row = 0; row < l; row++) {
            for (int col = 0; col < l / 2; col++) {
                int temp = A.get(row).get(col);
                A.get(row).set(col, A.get(row).get(l - 1 - col));
                A.get(row).set(l - 1 - col, temp);

            }
        }

    }

    public int consecutive1s13_2(String A) {
        int count = 0, leftCount = 0, rightCount = 0, maxCount = 0, count1 = 0, idx = 0;

        //count of 1s
        for (int x = 0; x < A.length(); x++) {
            if (A.charAt(x) == '1') {
                count1 += 1;
            }
        }

        for (int y = 0; y < A.length(); y++) {
            if (A.charAt(y) == '0') {
                break;
            }
            idx += 1;
        }
        leftCount = idx;
        System.out.println(idx);

        if (leftCount == A.length() - 1) {
            return leftCount;
        }

        //Start with
        for (int i = idx + 1; i < A.length(); i++) {
            if (A.charAt(i) == '1') {
                rightCount += 1;
            } else {
                count = leftCount + rightCount + 1;
                count = count > count1 ? count - 1 : count;
                if (count > maxCount) {
                    maxCount = count;
                }
                leftCount = rightCount;
                rightCount = 0;
            }
        }
        count = leftCount + rightCount + 1;
        count = count > count1 ? count - 1 : count;
        if (count > maxCount) {
            maxCount = count;
        }
        System.out.println(maxCount);
        return maxCount;
    }

    public int specialIndex15_3(int[] A) {
        int l = A.length, tot_eve_sum = 0, tot_odd_sum = 0, evenSum = 0, oddSum = 0, res = 0;
        int[] evenPrefSum = new int[l], oddPrefSum = new int[l];

        //creting even and odd sum array
        for (int i = 0; i < l; i++) {
            if (i % 2 == 0) {
                evenSum += A[i];
            } else {
                oddSum += A[i];
            }
            evenPrefSum[i] = evenSum;
            oddPrefSum[i] = oddSum;
        }
        for (int j = 0; j < l; j++) {
            evenSum = getsum(evenPrefSum, 0, j - 1) + getsum(oddPrefSum, j + 1, l - 1);
            oddSum = getsum(oddPrefSum, 0, j - 1) + getsum(evenPrefSum, j + 1, l - 1);
            if (evenSum == oddSum) {
//                System.out.println(j);
                res += 1;
            }
        }

        return res;
    }

    //democracy
    public int majorityElement15_4(final int[] A) {
        int count = 0, val = 0;
        for (int i = 0; i < A.length; i++) {
            if (count == 0) {
                count += 1;
                val = A[i];
            } else if (A[i] == val) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        System.out.println(val);
        return val;
    }

    public int powerWithModulo18_1(int A, int B, int C) {
        //need to return a^b%c
        long res = 1;
        for (int i = 1; i <= B; i++) {
            res = (res) * (A % C);
            res = res % C;
            System.out.println((int) res);
        }
        return (int) res;

    }

    public int modArray18_2(int[] A, int B) {
        int countPow10 = 0, l = A.length;
        long res = 0, multiple10 = 1;
        for (int i = l - 1; i >= 0; i--) {
            res = (res % B) + (multiple10 * A[i] % B);
            res = res % B;
            multiple10 = multiple10 % B * 10;
        }
        System.out.println(res);
        return (int) res;
    }

    public String largestNumber(final int[] A) {
        String re = "";
        Integer[] a = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            a[i] = A[i];
        }

        Arrays.sort(a, new LargestNumberComparator());
        boolean flag = false;
        for (int i = 0; i < A.length; i++) {
            if (!flag) {
                if (a[i] == 0) {
                } else {
                    flag = true;
                    re += a[i].toString();
                }

            } else {
                re += a[i].toString();
            }
        }
//        System.out.println(re);
        if (re == "") {
            re = "0";
        }
        System.out.println(re);
        return re;

    }

    public int[] sortColors19_HW_1(int[] A) {
        int countRed = 0, countWhite = 0, countBlue = 0, idx = 0;
        //getting count of all
        for (int i = 0; i < A.length; i++) {
            switch (A[i]) {
                case 0:
                    countRed += 1;
                    break;
                case 1:
                    countWhite += 1;
                    break;
                default:
                    countBlue += 1;
            }
        }

        for (int red = 0; red < countRed; red++) {
            A[red] = 0;

        }
        for (int white = countRed; white < countRed + countWhite; white++) {
            A[white] = 1;
        }
        for (int blue = countRed + countWhite; blue < A.length; blue++) {
            A[blue] = 2;
        }
        return A;
    }

    public int LongestSubArrayZeroSum22_1(int[] A) {
        int res = 0, maxLength = 0;
        double sum = 0;
        HashMap<Double, ArrayList<Integer>> commsum = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum == 0) {
                if (i + 1 > maxLength) {
                    maxLength += i + 1;
                }

            } else {
                if (commsum.get(sum) != null) {
                    ArrayList<Integer> val = commsum.get(sum);
                    int l = val.size();
                    if (l == 1) {
                        val.add(i);
                    } else {
                        val.set(1, i);
                    }
                    if (val.get(1) - val.get(0) > maxLength) {
                        maxLength = val.get(1) - val.get(0);
                    }
                } else {
                    ArrayList<Integer> newval = new ArrayList<>();
                    newval.add(i);
                    commsum.put(sum, newval);
                }
            }

        }

//        System.out.println(maxLength);
        return maxLength;
    }

    public int[][] rowToColumnZero11_HW_5(int[][] A) {
        int m = A.length, n = A[0].length;
        boolean row[] = new boolean[m];
        boolean col[] = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (row[i] == true) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (col[j] == true) {
                for (int i = 0; i < m; i++) {
                    A[i][j] = 0;
                }
            }
        }
        return A;
    }

    public void printFrom1toA_23_4(int A) {
        if (A == 1) {
            System.out.print("1");
        } else {
            printFrom1toA_23_4(A - 1);
            System.out.print(" ");
            System.out.print(A);
        }
    }

    public int checkPalindrome23_3(String A) {
        return checkPalindromeHelper23_3(A, 0, A.length()) ? 1 : 0;
    }

    public boolean checkPalindromeHelper23_3(String A, int strt, int end) {
//        int res = -2;
        if (strt >= end) {
            return true;
        } else {
            if (A.charAt(strt) == A.charAt(end - 1)) {
                return true && checkPalindromeHelper23_3(A, strt + 1, end - 1);
            } else {
                return false;
            }
        }
    }

    public ArrayList<Integer> alternatingSubarray10_HW_1(ArrayList<Integer> A, int B) {

        //Just check if the alternating order is maintained via sliding window, the bigger the size, more mid results will be added in results array
        //if it breaks then, shift the index to the breaking index
        ArrayList<Integer> res = new ArrayList<>();
        int l = A.size(), s = 2 * B + 1, counter = 0, idx = 1, resIdx = 0, size = 1;
        if (size >= s) {
            res.add(0);
        }
        while (idx < l) {
            if (A.get(idx) != A.get(idx - 1)) {
                size += 1;
            } else {
                size = 1;
            }

            if (size >= s) {
                res.add(idx - B);
            }
            idx += 1;
        }
//        printArrayList(res);
        return res;

    }

    public int pow25_2(int A, int B, int C) {
        if (A == 0) {
            return 0;
        } else if (B == 0) {
            return 1;
        } else {
            double res = pow25_2(A % C, B / 2, C) % C;
            double ret = (res) * (res) % C;
            double ans = 0;

            if (B % 2 == 0) {
                ans = (ret + C) % C;
//                System.out.println(ans);
                return (int) ans;
            } else {
                ans = ((ret * A % C) + C) % C;
//                System.out.println(ans);
                return (int) ans;
            }
        }
    }

    public int unsetIthBit17_4(int A, int B) {
        return 0;
    }

    public void reverseArray(int[] A, int strt, int end) {

        for (int i = strt; i <= (strt + end) / 2; i++) {
            int temp = A[i];
            A[i] = A[end - i + strt];
            A[end - i + strt] = temp;
        }
    }

    public int[][] multipleLeftRotation(int[] A, int[] B) {
        int l = A.length, n = B.length;
        int[][] res = new int[n][l];
        reverseArray(A, 0, l - 1);
//        printArray(A);
        for (int i = 0; i < n; i++) {
            int[] ans = new int[l];
            ans = A.clone();
            int k = B[i] % l;
            reverseArray(ans, 0, ans.length - 1 - k);
//            printArray(ans);
            reverseArray(ans, l - k, l - 1);
            printArray(ans);
            res[i] = ans;
//            printArray(ans);
        }
        return res;
    }

    public void reverseString(String A, int idx) {
        int l = A.length();
        if (idx == 0) {
            System.out.print(A.charAt(idx));
            ;
        } else {
            System.out.print(A.charAt(idx));
            reverseString(A, idx - 1);
        }
    }

    public int christmasTree13_2(int[] A, int[] B) {
        int l = A.length;
        long sum = 0, ans = Long.MAX_VALUE;
        for (int i = 1; i < l - 1; i++) {
            boolean leftFlag = false;
            boolean rightFlag = false;
            long leftmin = Long.MAX_VALUE, rightmin = Long.MAX_VALUE, ele = A[i];
            sum = B[i];
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    if (B[j] < leftmin) leftmin = B[j];
                    leftFlag = true;
                }
            }
            sum += leftmin;
            for (int k = i + 1; k < l; k++) {
                if (A[k] > A[i]) {
                    if (B[k] < rightmin) rightmin = B[k];
                    rightFlag = true;
                }
            }
            if (rightFlag & leftFlag) {
                sum += rightmin;
                if (sum < ans) ans = sum;
            }

        }
        return (int) ans;
    }

    public String longestCommonPrefix20(String[] A) {
        int l = A.length, idx, count = 0;
        if (l == 0) return "";
        else if (l == 1) return A[0];
        idx = A[0].length();
        for (int i = 1; i < l; i++) {
            idx = idx > A[i].length() ? A[i].length() : idx;
            for (int j = 0; j < idx; j++) {
                if (A[i].charAt(j) == A[0].charAt(j)) {
                    count += 1;
                }

            }
            idx = count;
            count = 0;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            res.append(A[0].charAt(i));
        }
        return res.toString();
    }

    public int[] commonElements21(int[] A, int[] B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Integer, Integer> ha = new HashMap<>();
        HashMap<Integer, Integer> hb = new HashMap<>();
        for (int x : A) {
            if (ha.get(x) == null) {
                ha.put(x, 1);
            } else {
                ha.replace(x, ha.get(x) + 1);
            }
        }

        for (int x : B) {
            if (hb.get(x) == null) {
                hb.put(x, 1);
            } else {
                hb.replace(x, hb.get(x) + 1);
            }
        }


        for (Map.Entry<Integer, Integer> x : ha.entrySet()) {
            if (hb.get(x.getKey()) != null) {
                int n = Math.min(x.getValue(), hb.get(x.getKey()));
                for (int i = 0; i < n; i++) {
                    res.add(x.getKey());
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    public String littlePonyAndTwoSubsequence(String A) {
        int minC = 'z', idx_strt = 0, idx_end = 0, mincc = 'z';
        for (int i = 0; i < A.length() - 1; i++) {
            if (A.charAt(i) < minC) {
                minC = A.charAt(i);
                idx_strt = i;
            }
        }
        for (int i = idx_strt + 1; i < A.length(); i++) {
            if (A.charAt(i) < mincc) {
                mincc = A.charAt(i);
                idx_end = i;
            }
        }
//        System.out.println( Character.toString(A.charAt(idx_strt))+Character.toString(A.charAt(idx_end)));

        return Character.toString(A.charAt(idx_strt)) + Character.toString(A.charAt(idx_end));
    }

    public long bitWiseOr1(int A, int[] B) {
        int l = A;
        long sum = (long) ((l) * (l + 1) / 2), sumZero = 0, subSize = 0;
        for (int i = 0; i < A - 1; i++) {
            if (B[i] == 0) {
                subSize += 1;
            } else {
                sum -= (long) ((subSize) * (subSize + 1)) / 2;
                subSize = 0;
            }
        }
        if (B[l - 1] == 0) {
            subSize += 1;

        }
        sum -= (long) ((subSize) * (subSize + 1)) / 2;

        return sum;
    }

    public ArrayList<Integer> getPrime(int A) {
        int[] prm = new int[A + 1];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 2; i < A + 1; i++) {
            if (prm[i] != -1) {
                for (int j = i + i; j < A + 1; j += i) {
                    prm[j] = -1;
                }
                prm[i] = i;
            }
        }
//        printArray(prm);
        for (int i = 0; i < A + 1; i++) {
            if (prm[i] > 1) res.add(prm[i]);
        }

        return res;
    }

    public String convertToTitle(int A) {
        ArrayList<Integer> st = new ArrayList<>();
        int base = 26;
        StringBuilder sb = new StringBuilder();
        while (A / base > 0) {
            st.add(A % base);
            A /= base;
        }
        if (A % base != 0) st.add(A % base);
        for (int i = st.size() - 1; i >= 0; i--) {
            sb.append((char) (st.get(i) + 64));
        }
        printArrayList(st);
        return sb.toString();
    }

    public int ncr_39_np(int A, int B, int C) {
        int[][] mat = new int[A][B];
        mat[0][0] = 1;
        for(int i = 1;i<A;i++){
            for(int j = 0;j<B;j++){
                    mat[i][j] = j-1 >= 0 & j<=i ? (mat[i-1][j-1] + mat[i-1][j])%C:(mat[i-1][j] + 1)%C;
            }
        }
        return mat[A-1][B-1];
    }
    public int ncr_39_p(int A, int B, int C) {

    }
}

class Assignments {
    public static void reverseString(String A, int idx) {
        int l = A.length();
        if (idx == 0) {
            System.out.print(A.charAt(idx));
            ;
        } else {
            System.out.print(A.charAt(idx));
            reverseString(A, idx - 1);
        }
    }

    public static void main(String[] args) {
        Solution x = new Solution();
        System.out.println(x.ncr(5, 2, 13));
//        System.out.println(x.convertToTitle(943566));
//        x.bitWiseOr1(3,new int[]{1,0,1});
//        x.commonElements21(new int[]{1,2,2,1
//        },new int[]{2,3,1,2});
//        x.littlePonyAndTwoSubsequence("ksdjgha");

//        System.out.println((-40) % 7);
        ArrayList<ArrayList<Integer>> input_2d = new ArrayList(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)));
        int[] input_arr = new int[]{1, 2, 3, 4, 5, 6, 7};
//        x.printArrayList(x.getPrime(20));
//        boolean row[] = new boolean[2];
//        Scanner sc;
//        System.out.println(x.christmasTree13_2(new int[]{ 5, 9, 10, 4, 7, 8 },new int[]{5, 6, 4, 7, 2, 5}));
//        sc = new Scanner(System.in);
//        String ip = sc.nextLine();
//        reverseString(ip,ip.length()-1);
//        System.out.println(foo(3,5));


//
//        x.multipleLeftRotation(input_arr, new int[]{2, 4});
//        x.reverseArray(input_arr, 1, 4);
//        x.printArray(input_arr);
//        x.printArray(input_arr);
//        System.out.println(x.pow25_2(71045970, 41535484, 64735492));

//        x.alternatingSubarray10_HW_1(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0)), 0);
//        System.out.println(x.checkPalindrome23_3("naman"));

//        x.printFrom1toA_23_4(77);
//        System.out.println();
//        String ab = "Hello are you there";

//        System.out.println();
//        x.LongestSubArrayZeroSum22_1(new int[]{5, -3, 2, 1, 0});
//        int[] input_arr = new int[]{1,2,3,4,5,6,7,8,9};
//        x.rotatemat(input_2d);
//        x.specialIndex15_3(input_arr);
//        x.majorityElement15_4(input_arr);
//x.consecutive1s13_2("00000011111111");
//        System.out.println(x.goodsubarray(new ArrayList(Arrays.asList(1, 2, 3, 4, 5)), 4));

//        System.out.println(x.pickboth(new int[]{5,-2,3,1,2},3));


//        new Solution().maxProfit(new int[]{1,2,3,4,5});
//        x.powerWithModulo18_1(637759701,48998,296839866);

//        x.modArray18_2(new int[]{ 8, 2, 5, 6, 7, 6, 2, 6, 2 }, 3);
//        x.largestNumber(input_arr);
//        HashMap<Integer,List<Integer>> hm = new HashMap<>();
//        hm.put(0, Arrays.asList(0,1));
//        System.out.println(hm.get(2));
//        List<Integer> newval = hm.get(2) != null?Arrays.asList(2):;
//        char tst = 9;
//        System.out.println(25 ^ 0);
//        System.out.println(tst);
    }
}
