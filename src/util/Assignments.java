package util;

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

class Assignments {

    public static void main(String[] args) {
        Solution x = new Solution();
//        System.out.println(x.ncr_39_p(5, 2, 13));
        System.out.println(x.convertToTitle(97504));
//        x.bitWiseOr1(3,new int[]{1,0,1});
        //ELUB
//        x.commonElements21(new int[]{1,2,2,1
//        },new int[]{2,3,1,2});
//        x.littlePonyAndTwoSubsequence("ksdjgha");

//        System.out.println((-40) % 7);
        ArrayList<ArrayList<Integer>> input_2d = new ArrayList(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)));
        int[] input_arr = new int[]{1, 2, 3, 4, 6,5,4};
        int[] cop = input_arr;

        Arrays.sort(input_arr);
        x.printArray(input_arr);
        x.printArray(cop);
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


//        new util.Solution().maxProfit(new int[]{1,2,3,4,5});
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
}
