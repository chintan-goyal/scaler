package assignments;

public class Day45 {


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

    public int searchInsert(int[] A, int B) {
        long s = 0, e = A.length - 1;
        int m;
        while (s <= e) {
            m = (int) (s + e) / 2;
            int el = A[m];
            if (el == B) return m;
            if (B < el) e = m - 1;
            else s = m + 1;
        }
        return (int) (s);
    }

    public int findPeak(int[] A) {
        int s = 0, e = A.length - 1, m = 0, l = A.length;
        if (l == 1) return A[0];
        else if (l == 2) return Math.max(A[0], A[1]);
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (A[1] <= A[0]) return A[0];
            else if (A[l - 1] >= A[l - 2]) return A[l - 1];

            else {
                if (A[m] >= A[m - 1] & A[m] >= A[m + 1]) return A[m];
                else {
                    if (A[m] < A[m - 1]) e = m - 1;
                    else if (A[m] < A[m + 1]) s = m + 1;
                }
            }
        }
        return m;
    }

    public int[] searchRange(final int[] A, int B) {
        int s = 0, l = A.length, e = l - 1, m = 0, md = 0;
        int[] res = new int[]{-1, -1};
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (A[m] > B) e = m - 1;
            else if (A[m] < B) s = m + 1;
            else {
                res[0] = m;
                res[1] = m;
                break;
            }
        }
        s = 0;
        e = m - 1;
        md = m;
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (A[m] < B) s = m + 1;
            else if (A[m] == B) {
                e = m - 1;
                res[0] = m;
            }
        }

        s = md + 1;
        e = l - 1;

        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (A[m] > B) e = m - 1;
            else if (A[m] == B) {
                s = m + 1;
                res[1] = m;
            }
        }
        return res;
    }

    public int findUnique(int[] A) {
        int s = 0, l = A.length, e = l - 1, m = 0, el = A[0];
        if (l <= 2) return A[0];
        else if (A[0] != A[1]) return A[0];
        else if (A[l - 1] != A[l - 2]) return A[l - 1];
        while (s <= e) {
            m = (int) ((long) (s + e) / 2);
            if (m == 0 & A[0] != A[1]) return A[0];
            else if (m == l - 1 & A[l - 1] != A[l - 2]) return A[l - 1];
            else {
                if (A[m] != A[m - 1] & A[m] != A[m + 1]) return A[m];
                else {
                    if (m % 2 != 0) {
                        if (A[m] == A[m - 1]) s = m + 1;
                        else e = m - 1;
                    } else {
                        if (A[m] == A[m + 1]) s = m + 1;
                        else e = m - 1;
                    }

                }
            }

            // System.out.println(s +" "+m+" "+e+" "+A[m] );
        }
        return A[m];
    }
    public int stairCase(int A) {
        int res = 0, mul = 1;
        while(A>0){
            if (A>=mul){
                A-= mul;
            }
            else{
                break;
            }
            res += 1;
            mul += 1;
        }
        return res;
    }


    public static void main(String[] args) {
        Day45 s = new Day45();
//        StringBu

//        System.out.println(s.getMaxMinDiff(new int[]{7, 8, 6, 4, 6, 7, 3, 10, 2, 3, 8, 1, 10, 4, 7, 1, 7, 3, 7, 2, 9, 8, 10, 3, 1, 3, 4, 8, 6, 10, 3, 3, 9, 10, 8, 4, 7, 2, 3, 10, 4, 2, 10, 5, 8, 9, 5, 6, 1, 4, 7, 2, 1, 7, 4, 3, 1, 7, 2, 6, 6, 5, 8, 7, 6, 7, 10, 4, 8, 5, 6, 3, 6, 5, 8, 5, 5, 4, 1, 8, 9, 7, 9, 9, 5, 4, 2, 5, 10, 3, 1, 7, 9, 10, 3, 7, 7, 5, 10, 6, 1, 5, 9, 8, 2, 8, 3, 8, 3, 3, 7, 2, 1, 7, 2, 6, 10, 5, 10, 1, 10, 2, 8, 8, 2, 2, 6, 10, 8, 8, 7, 8, 4, 7, 6, 7, 4, 10, 5, 9, 2, 3, 10, 4, 10, 1, 9, 9, 6, 1, 10, 7, 4, 9, 6, 7, 2, 2, 6, 10, 9, 5, 9, 2, 1, 4, 1, 5, 5, 5, 5, 8, 7, 4, 2, 8, 6, 10, 7, 3, 2, 8, 9, 6, 8, 5, 2, 9, 6, 10, 8, 6, 4, 9, 9, 4, 2, 9, 10, 7, 5, 4, 4, 4, 9, 7, 1, 5, 9, 9, 9, 10, 8, 8, 7, 5, 4, 1, 4, 1, 10, 3, 6, 5, 1, 6, 10, 5, 7, 10, 3, 3, 5, 8, 8, 6, 5, 9, 2, 3, 9, 10, 4, 7, 9, 1, 3, 2, 1, 6, 2, 2, 1, 9, 6, 1, 7, 5, 7, 3, 6, 9, 7, 3, 9, 5, 8, 3, 5, 1, 7, 3, 10, 10, 1, 9, 2, 4, 2, 2, 1, 4, 5, 1, 4, 10, 2, 10, 7, 10, 4, 4, 9, 1, 6, 7, 7, 5, 1, 1, 5, 7, 3, 7, 8, 6, 7, 10, 9, 8, 3, 9, 3, 10, 10, 7, 1, 3, 8, 7, 2, 4, 3, 2, 6, 10, 10, 2, 5, 10, 2, 1, 8, 6, 9, 8, 1, 5, 9, 1, 5, 3, 10, 7, 2, 1, 5, 3, 3, 3, 1, 6, 6, 3, 10, 1, 3, 9, 4, 9, 1, 5, 1, 10, 2, 10, 7, 3, 6, 5, 5, 10, 10, 4, 7, 1, 6, 1, 3, 10, 5, 4, 6, 2, 8, 5, 4, 2, 5, 7, 10, 5, 3, 3, 7, 5, 2, 3, 9, 9, 10, 3, 9, 9, 9, 7, 9, 4, 9, 4, 4, 4, 9, 1, 5, 8, 7, 9, 10, 1, 7, 9, 8, 10, 1, 4, 4, 4, 8, 4, 3, 7, 6, 3, 7, 6, 9, 8, 10, 7, 1, 5, 2, 1, 5, 9, 8, 1, 9, 7, 3, 5, 8, 10, 4, 10, 3, 9, 4, 1, 2, 8, 9, 10, 2, 6, 5, 10, 3, 6, 8, 5, 10, 10, 5, 6, 10, 4, 6, 8, 1, 9, 2, 10, 10, 8, 9, 3, 6, 4, 5, 10, 1, 3, 1, 2, 10, 7, 3, 2, 3, 1, 8, 4, 2, 2, 10, 1, 6, 7, 8, 8, 5, 1, 7, 5, 8, 5, 9, 6, 9, 3, 7, 1, 7, 7, 5, 7, 3, 9, 10, 7, 1, 8, 1, 2, 1, 2, 4, 8, 8, 3, 7, 5, 6, 3, 1, 3, 10, 1, 10, 10, 5, 6, 2, 1, 4, 8, 9, 9, 7, 1, 5, 7, 8, 7, 1, 10, 8, 6, 10, 8, 9, 6, 4, 4, 9, 4, 8, 10, 4, 8, 9, 8, 5, 2, 10, 1, 10, 9, 9, 6, 9, 5, 4, 8, 2, 4, 9, 1, 10, 8, 10, 10, 4, 3, 5, 4, 8, 2, 3, 3, 1, 3, 2, 8, 6, 2, 8, 5, 2, 8, 2, 2, 2, 8, 1, 5, 1, 9, 6, 2, 7, 7, 3, 2, 10, 7, 5, 9, 1, 9, 2, 1, 3, 3, 10, 8, 6, 7, 5, 7, 4, 8, 10, 8, 5, 10, 2, 8, 1, 7, 1, 9, 6, 4, 10, 5, 2, 6, 5, 2, 6, 6, 5, 10, 9, 4, 9, 6, 3, 3, 3, 8, 1, 4, 5, 7, 4, 7, 4, 4, 5, 5, 4, 10, 8, 3, 6, 9, 10, 1, 3, 5, 8, 7, 6, 8, 2, 4, 4, 4, 9, 6, 2, 1, 9, 8, 7, 4, 6, 1, 9, 1, 5, 2, 2, 4, 6, 10, 4, 5, 2, 6, 1, 9, 4, 6, 7, 6, 10, 10, 1, 8, 7, 4, 8, 7, 2, 6, 1, 7, 6, 1, 9, 2, 3, 3, 7, 10, 2, 1, 5, 3, 8, 5, 1, 4, 3, 9, 1, 4, 8, 1, 1, 4, 5, 10, 3, 8, 5, 3, 6, 3, 5, 5, 4, 9, 7, 1, 9, 10, 3, 3, 4, 2, 9, 4, 5, 3, 3, 5, 6, 2, 8, 6, 8, 2, 7, 10, 9, 2, 4, 4, 4, 8, 10, 9, 7, 8, 1, 5, 9, 5, 9, 2, 7, 9, 6, 3, 2, 10, 10, 7, 1, 7, 5, 10, 10, 1, 9, 10, 4, 2, 5, 9, 10, 7, 8, 8, 4, 8, 2, 3, 3, 2, 6, 1, 10, 1, 5, 1, 2, 4, 8, 5, 2, 2, 4, 1, 4, 3, 2, 8, 6, 7, 6, 5, 3, 3, 2, 8, 3}));

        StringBuilder a = new StringBuilder();
//        System.out.println(a.length()                       );
//        s.subUnsort(new int[]{1, 3, 3, 5, 7, 9, 11, 11, 9, 7, 9, 11, 16, 17, 20, 20});
//        s.countSort(new int[]{6, 3, 3, 6, 7, 8, 7, 3, 7});
//        System.out.println(s.makeUnique(new int[]{1,2,2,3,4,4,5}));
//        System.out.println(s.findRank("DTNGJPURFHYEW"));
//        System.out.println( 75% 7);

        System.out.println(s.searchInsert(new int[]{6, 8}, 9));
        //6


    }
}
