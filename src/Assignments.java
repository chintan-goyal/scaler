import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

class LargestNumberComparator implements Comparator<Integer>{
    public int compare(Integer a, Integer b){
        String ab = a.toString()+b.toString();
        String ba = b.toString()+a.toString();
        if(Double.parseDouble(ab) > Double.parseDouble(ba)){
            return -1;
        }
        else if (a == b){
            return 0;
        }
        else {
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
            multiple10 = multiple10%B * 10;
        }
        System.out.println(res);
        return (int) res;
    }

    public String largestNumber(final int[] A) {
        String re = "";
        Integer[] a = new Integer[A.length];
        for(int i =  0;i<A.length;i++){
            a[i] = A[i];
        }

        Arrays.sort(a,new LargestNumberComparator());
        boolean flag = false;
        for(int i =  0;i<A.length;i++){
            if (!flag){
                if (a[i]==0){}
                else{
                    flag = true;
                    re += a[i].toString();
               }

            }
            else{
                re += a[i].toString();
            }
        }
//        System.out.println(re);
        if(re == ""){
            re = "0";
        }
        System.out.println(re);
        return re;

    }

    public int[] sortColors19_HW_1(int[] A) {
        int countRed = 0, countWhite = 0, countBlue= 0, idx = 0;
        //getting count of all
        for(int i = 0; i<A.length; i++){
            switch (A[i]){
                case 0:
                    countRed+= 1;
                    break;
                case 1:
                    countWhite+= 1;
                    break;
                default:
                    countBlue+= 1;
            }
        }

        for(int red = 0;red<countRed;red++){
            A[red] = 0;

        }
        for(int white = countRed;white<countRed+countWhite;white++){
            A[white] = 1;
        }
        for(int blue = countRed+countWhite;blue<A.length;blue++){
            A[blue] = 2;
        }
        return A;
    }



}


class Assignments {
    public static void main(String[] args) {
        Solution x = new Solution();
//        System.out.println((-40) % 7);
        ArrayList<ArrayList<Integer>> input_2d = new ArrayList(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)));
        int[] input_arr = new int[]{3, 30, 34, 5, 9};
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
        char tst = 97;
        System.out.println(tst);
    }
}
