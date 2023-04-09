package assignments;

import util.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day39 {

    public int findRank(String A) {
        int res = 0, l = A.length();
        char[] arr = new char[A.length()];

        for (int i = 0; i < l; i++) arr[i] = A.charAt(i);
        //Sorted characters arranged with rank
        Arrays.sort(arr);

        HashMap<Character, Integer> hm = new HashMap<>();
        //Created dictionary with elements with their rank
        for (int i = 0; i < l; i++) hm.put(arr[i], i);

        for (int i = 0; i < l - 1; i++) {
            //Main logic is to get the total permutation possible for lesser character, IE : - for cabd: - for c, a and b will come first,Hence total permutations will be 2*3!. Doing that for across the array
            res += hm.get(A.charAt(i)) * Solution.getFactorial(l - 1 - i, 1000003);


            for(Map.Entry<Character,Integer> x : hm.entrySet()){
                if (x.getValue()>hm.get(A.charAt(i))) hm.replace(x.getKey(),x.getValue()-1);
            }
        }
        return (res + 1+1000003)%1000003;
    }

    public static void main(String[] args) {
        Day39 s = new Day39();
        System.out.println(s.findRank("DTNGJPURFHYEW"));


    }
}
