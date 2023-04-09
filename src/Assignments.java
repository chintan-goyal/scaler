

import java.util.*;
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

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
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public void printArray(int[] A) {
        System.out.println("Printing the list");
        for (int x : A
        ) {
            System.out.print(x + " ");
        }
        System.out.println();
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

    int solve_Test2(int A, int B) {
        long res = 0;
        for (int i = B; i < A + B; i++) {
            res += Math.pow(2, i);

        }
        return (int) res;
    }

    public int SubArraywithLeastAvg12_1(int[] A, int B) {
        int l = A.length;
        int sum = 0, idx = 0;
        float avg = 0;
        for (int i = 0; i < B; i++) {
            sum += A[i];
        }
        avg = (float) sum / B;
//        System.out.println(avg);
        for (int i = 1; i <= l - B; i++) {
            sum -= A[i - 1];
            sum += A[i + B - 1];
            if (avg > (float) sum / B) {
                avg = (float) sum / B;
                idx = i;
            }
        }
        return idx;
    }

    public int[] maximumPositivity(int[] A) {
        int strt_idx = 0, cnt = 0, max_cnt = 0, res_idx = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                if (cnt == 0) {
                    strt_idx = i;
                }
                cnt += 1;
                if (cnt > max_cnt) {
                    res_idx = strt_idx;
                    max_cnt = cnt;
                }
            } else {
                cnt = 0;
            }
        }
//        System.out.println(res_idx +" "+ max_cnt);
        int[] res = new int[max_cnt];
        for (int i = 0; i < max_cnt; i++) {
            res[i] = A[res_idx + i];
//            System.out.println(i + " ");
        }
        return res;
    }

    public long bitWiseOr(int A, int[] B) {
        long sum = 0;
        int[] prefSUm = getprefsum(B);
        for (int i = 0; i < A; i++) {
            for (int j = i; j < A; j++) {
                if (getsum(prefSUm, i, j) > 0) sum += 1;
            }
        }
        return sum;
    }

    public int isMagic(int A) {
        if (A / 10 == 0) {
            return A;
        } else {
            int res = A % 10 + isMagic(A / 10);
            System.out.println("res is " + res);
            if (res / 10 != 0) {
                res = isMagic(res);
            }
            return res;

        }
    }

    public int JosephsRecurssion(ArrayList<Integer> A, int B, int strt) {
        int l = A.size();
        if (l == 1) return A.get(0);
        int idx = strt;
        idx = strt > l - 1 ? 0 : strt;
        idx += B - 1;
        idx = idx > l - 1 ? idx %= l : idx;
        A.remove(idx);
//        printArrayList(A);
        return JosephsRecurssion(A, B, idx);

    }

    ;

    //    public int kthSymbol(ArrayList<ArrayList<Integer>> ipLst, int curr_row, int )
    public int colorful21(int A) {
        ArrayList<Integer> input_arr = new ArrayList<Integer>();
        while (A > 0) {
            input_arr.add(A % 10);
            A /= 10;
        }
        HashMap<Integer, Integer> ans = new HashMap<>();
        int l = input_arr.size();
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {

                int mul = 1;
                for (int k = i; k <= j; k++) {
                    mul *= input_arr.get(k);
                    System.out.print(input_arr.get(k) + " ");
                }
                System.out.println();
                if (ans.get(mul) != null) return 0;
                ans.put(mul, 1);

            }
        }
        return 1;
    }

    public int solve_1(int[] A) {
        int idx = 0, l = A.length;
        int count = 1, idx_strt = 0, top_count = 0, res_idx = 0;
        for (int i = 1; i < l; i++) {
            if (A[i - 1] < A[i]) {
                count += 1;
                if (i == l - 1 & count > top_count)
                    top_count = count;
            } else {
                if (count > top_count) {
                    top_count = count;
                    res_idx = idx_strt;
                }
                count = 1;
                idx_strt = i;
            }
        }
        System.out.println(res_idx + " " + top_count);
        return top_count;
    }

    public int solve_x(String[] A) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length(); j++) {
                res.put((int) A[i].charAt(j), 1);
            }
        }
        for (int i = 97; i <= 122; i++) if (res.get(i) == null) return 0;
        return 1;
    }

    public int[] solve_3(String A, int[][] B) {
        int res[] = new int[B.length];
        int countarr[] = new int[A.length()];
        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count += 1;
            countarr[i] = count;
        }

        for (int j = 0; j < B.length; j++) {
            if (B[j][0] == 0) res[j] = countarr[B[j][1]];
            else res[j] = countarr[B[j][1]] - countarr[B[j][0] - 1];

        }
        printArray(res);
        return res;

    }

    public int solve_4(TreeNode A, TreeNode B) {
        if (A == null & B == null) return 0;
        if ((A == null & B != null) | (A != null & B == null)) return -1;

        if (A.val == B.val) {
            int res = 0;
            if (A.left != null) {
                if (A.right != null) {
                    if (B.left == null | B.right == null) {
                        return -1;
                    } else {
                        if (A.left.val == B.left.val && A.right.val == B.right.val) {
                            int leftCheck = solve_4(A.left, B.left);
                            int rightCheck = solve_4(A.right, B.right);
                            if (leftCheck != -1 & rightCheck != -1) return leftCheck + rightCheck;
                            else return -1;
                        } else if (A.right.val == B.left.val && A.left.val == B.right.val) {
                            int leftCheck = solve_4(A.left, B.left);
                            int rightCheck = solve_4(A.right, B.right);
                            if (leftCheck != -1 & rightCheck != -1) return 1 + leftCheck + rightCheck;
                            else return -1;
                        }
                        return -1;

                    }
                }


            }
        } else {
            return -1;
        }
        return 1;
    }

    public int[] solve(int A, int[][] B) {
        int[] res = new int[A];
        int l = B.length, sum = 0;
        for (int i = 0; i < l; i++) {
            res[B[i][0] - 1] += B[i][2];
            if (B[i][1] <= A - 1) {
                res[B[i][1]] -= B[i][2];
            }
        }

        for (int i = 0; i < l; i++) {
            sum += res[i];
            res[i] = sum;
        }
        printArray(res);
        return res;

    }

    public int trap33(final int[] A) {
        int s = A.length, sum = 0;
        int maxl = 0, maxr = 0;
        int[] l = new int[s];
        int[] r = new int[s];
        l[0] = 0;
        r[s - 1] = 0;
        for (int i = 1; i < s; i++) {
            if (A[i - 1] > maxl) {
                maxl = A[i - 1];
            }
            if (A[i] >= maxl) maxl = 0;
            l[i] = maxl;
        }
//        printArray(l);
        for (int i = s - 2; i >= 0; i--) {
            if (A[i + 1] > maxr) {
                maxr = A[i + 1];
            }
            if (A[i] >= maxr) maxr = 0;
            r[i] = maxr;
        }
//        printArray(r);
        for (int i = 0; i < s; i++) {
            sum += Math.min(l[i], r[i]) - A[i] > 0 ? Math.min(l[i], r[i]) - A[i] : 0;
//            System.out.print(Math.min(l[i],r[i]) + " ");
        }
        return sum;
    }

    public int firstMissingPositive(int[] A) {
        int counter = 0, temp = 0, res = 1, l = A.length;
        while (counter < l) {
            if ((A[counter] > 0) & (A[counter] < l + 1) & (A[counter] != counter + 1)) {
                if (A[counter] != A[A[counter] - 1]) {
                    temp = A[counter];
                    A[counter] = A[temp - 1];
                    A[temp - 1] = temp;
                } else counter++;

            } else {
                counter++;
            }
        }
        for (int i = 0; i < l; i++) {
            if (A[i] != i + 1) break;
            else res++;
        }
        return res;
//        printArray(A);
//        System.out.println(res);
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        int l = intervals.size(), s = 0, e = l-2, si, ei, nsi, nei;
        if(l <2) return intervals;
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        while(s<=e){
            si = intervals.get(s).start;
            ei = intervals.get(s).end;
            nsi = intervals.get(s+1).start;
            nei = intervals.get(s+1).start;

            if(ei>nsi){
                intervals.get(s+1).end = Math.max(ei,nei);
                intervals.get(s+1).start = Math.min(si,nsi);
                s++;
            }
            else res.add(intervals.get(s));
            s++;

        }

        return res;
    }

    public int findMaxAn(ArrayList<Integer> A) {
        int sum = 0;
        for(int i = 31;i>=0;i--){
            int count = 0;
            for(int j = 0; j<A.size(); j++){
                if((A.get(j) & (1<<i)) != 0) count++;
            }
            if (count > 1){
                sum += Math.pow(2,i);
                for(int k = 0; k < A.size(); k++){
                    if((A.get(k) & (1<<i)) == 0) A.set(k,0);
                }
            }
        }
        return sum;
    }
}



class Assignments {
    public static void main(String[] args) {
        Solution x = new Solution();
//        x.firstMissingPositive(new int[]{4, 2, 1, -5, 3, 5});
        System.out.println(9&(1<<0));

//        System.out.println(x.trap33(new int[]{1, 2}));
//        System.out.println(null == null);

//        x.solve_3("scaler", new int[][]{{0, 2}, {2, 4}});
//        x.solve(5,new int[][]{{1, 2, 10},  {2, 3, 20},  {2, 5, 25}});
//        System.out.println((int) "z".charAt(0));
//        x.solve_1(new int[]{16,3,3,6,7,8,17,13,7});
//        Sysem.out.println(x.colorful21(12));
//        x.maximumPositivity(new int[]{5, 6, 7, -2, 8, 9, 0, 1});
//        System.out.println(x.bitWiseOr(3,new int[]{1,0,1}));
//        System.out.println((long)Math.pow(2,4)-1);
//        System.out.println(4 & 1);

        ArrayList<ArrayList<Integer>> input_2d = new ArrayList(Arrays.asList(Arrays.asList(1, 2, 10), Arrays.asList(2, 3, 20), Arrays.asList(2, 5, 25)));
        int[] input_arr = new int[]{6, 9, 9, 8};
        ArrayList<Integer> input_Joseph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ip = new ArrayList<ArrayList<Integer>>();


//        ArrayList<Integer> fl = new ArrayList<>();
//        fl.add(0);
//        ip.add(fl);


//        for(int i = 1; i<=10;i++){
//            input_Joseph.add(i);
//        }
//        System.out.println(x.JosephsRecurssion(input_Joseph,8,0));
//        System.out.println(x.isMagic(83557));


//        System.out.println(x.SubArraywithLeastAvg12_1(new int[]{ 20, 1, 5, 2, 7, 5, 17  },6));
//        System.out.println(x.solve_Test2(5,4));
//        x.printFrom1toA_23_4(77);
//        x.LongestSubArrayZeroSum22_1(new int[]{5, -3, 2, 1, 0});
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
//        char tst = 97;
//        System.out.println(tst);
    }
}


class Matrix {


    // Define properties here
    int row, column;
    int[][] mat;

    // Define constructor here
    Matrix(int r, int c) {
        this.row = r;
        this.column = c;
        this.mat = new int[r][c];
    }

    void input(Scanner sc) {
        // Use the Scanner object passed as argument for taking input and not a new Scanner object
        for (int i = 0; i < this.row; i++) {
            String ip = sc.nextLine();
            String[] ele = ip.split(" ");
            for (int j = 0; j < this.column; j++) {
                this.mat[i][j] = Integer.parseInt(ele[j]);

            }
        }
        // Complete the function

    }

    Matrix add(Matrix x) {
        // Complete the function
        Matrix res = new Matrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                res.mat[i][j] = this.mat[i][j] + x.mat[i][j];
            }
        }
        return res;

    }

    Matrix subtract(Matrix x) {
        // Complete the function
        Matrix res = new Matrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                res.mat[i][j] = this.mat[i][j] - x.mat[i][j];
            }
        }
        return res;

    }

    Matrix transpose() {
        // Complete the function
        Matrix res = new Matrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                res.mat[i][j] = this.mat[j][i];
            }
        }
        return res;

    }

    void print() {
        // Complete the function
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.mat[i][j] + " ");
            }
            System.out.println();
        }


    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}
/*
    Matrix a = new Matrix(10, 5)  // 10 rows, 5 columns
	a.input()
	Matrix b = new Matrix(10, 5)  // 10 rows, 5 columns
	b.input()
    Matrix c1 = a.add(b)
    Matrix c2 = a.subtract(b)
    Matrix c3 = a.transpose()
    a.print()
*/
