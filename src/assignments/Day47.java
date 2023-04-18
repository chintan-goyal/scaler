package assignments;

import java.util.Arrays;
import java.util.List;

public class Day47 {


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


    public int getGCD(int A, int B){
        if(B%A == 0) return A;
        return getGCD(B%A, A);
    }

    public long getAthPosition(int A, int B, long N){
        return N/A + N/B - N/((A*B)/getGCD(A,B));
    }


    // public get
    public int GetAthNumber(int A, int B, int C) {
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

    public long getNumPainter(int[] C, int B, long T ){
        long res = 0;
        long time_remaining = 0;
        for(int i = 0;i<C.length;i++){
            long time_required =  (long)C[i]*B;
            if(time_remaining<time_required){
                res += 1;
                time_remaining = T;
            }
            time_remaining -= time_required;
        }

        return res;
    }
    public int paint(int A, int B, int[] C) {
        long s = 0, e = 0, m = 0;
        int mod = 10000003;
        long res = 0, num_painter = 0;
        int l = C.length;
        long min = C[0],max = 0;
        for(long x:C) {min = Math.max(min,x);
            max += x;
        }
        s = min*B;
        e = max*B;
        while (s <= e){
            m = (s+e)/2;
            num_painter = getNumPainter(C,B,m);
            if(num_painter> A) s = m+1;
            else {
                System.out.println(m);
                res = m;
                e = m-1;
            }
        }
//        System.out.println(res);
        return (int) ((res%mod)+mod)%mod;
    }

    public int getStudents(int[] A , long P ){
        int people = 0;
        long remainingPage = 0;
        for(int x:A){
            if(remainingPage < x){
                people += 1;
                remainingPage = P;
            }
            remainingPage -= x;
        }
        return people;

    }

    public int books(int[] A, int B) {
        long s = A[0], e = 0, m = 0, min = A[0];
        int res = 0;
        for(int x:A){
            min = Math.max(s,x);
            e += x;
        }
        s = min;
        while(s<=e){
            m = (s+e)/2;
            int num_students = getStudents(A,m);
            if(num_students > B ) s= m+1;
            else if (num_students == B) {
                res = (int) m;
                e = m-1;
            }
            else e = m-1;
        }
        return res == 0 ? (int) min: res;
    }



    public static void main(String[] args) {
        Day47 s = new Day47();

        StringBuilder a = new StringBuilder();

//        System.out.println(s.paint(1,1000000, new int[]{1000000,1000000}));
//        System.out.println(s.getNumPainter( new int[]{1000000,1000000},1000000,1000000));
//        System.out.println(2*(long)Math.pow(1000000,2)%10000003);
//        System.out.println(s.findMedianSortedArrays(Arrays.asList(-43, -25, -18, -15, -10, 9, 39, 40), Arrays.asList(-2)));
       
    }
}
