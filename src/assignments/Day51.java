package assignments;

//import com.sun.tools.javac.util.StringUtils;

public class Day51 {
    public String reverseString(String A) {
        StringBuilder sb = new StringBuilder();
        String[] input = A.split(" ");
        int l = input.length;
        for(int i = l-1;i>=0;i--){
            int size = input[i].length();
            if(size != 0) sb.append(input[i]+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public int multipleArrangement(int A, String B){
        int[] dic = new int[26];
        for(char  x: B.toCharArray()){
            dic[x-97]+= 1;
        }
        for(int x:dic) if(x%A != 0) return -1;
//        System.out.println((int)'a'-97);
        return 1;
    }

    public static void main(String[] args) {
        Day51 s = new Day51();
//        System.out.println(s.reverseString(" This   is blue  sky "));
        System.out.println(s.multipleArrangement(3, "aabbccabc"));


    }
}
