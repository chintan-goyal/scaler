class Solution2 {

    public int[][] solve(int[] A,int B,int C) {
        int[][] res = new int[B][C];
        int count = 0;
        int ur = 0;
        int lr = B-1;
        int lc = 0;
        int rc = C-1;
        while(count < B*C){
            for(int i = lc;i<=rc;i++){
                res[ur][i]= A[count];
                count++;
            }
            ur++;
            for(int j = ur;j<=lr;j++){
                res[j][rc]= A[count];
                count++;
            }
            rc--;
            for(int k = rc;k>=lc;k--){
                res[lr][k]= A[count];
                count++;
            }
            lr--;
            for(int l = lr;l>=ur;l--){
                res[l][lc]= A[count];
                count++;
            }
            lc++;
        }
    return  res;
    }

    public int count1(String A) {
        int res = 0;
        int count = 0;
        int l = A.length();
        int max_count = 0;
        int sub_count = 0;
        //Total count of 1st
        for(int i = 0;i<l;i++){
            if(A.charAt(i) == '1') {
                count++;
            }
        }

        //Create 1st subarray of length l
        for(int j = 0;j<count;j++){
            if(A.charAt(j)=='1'){
                max_count++;
            }
        }
        sub_count = max_count;
        for(int k = 1; k<(l-count);k++){
            if(A.charAt(k-1)=='1'){
                sub_count--;
            }
            if(A.charAt(k+count-1) == '1'){
                sub_count++;
            }

            if(sub_count>max_count){
                max_count = sub_count;
            }
        }

        if(max_count == count){
            res =  count;
        }
        else {
            res =  max_count+1;
        }


        System.out.println(res);
        return res;
    }
    public int[] prod(int[] A) {
        double mul = 1;
        int[] res = new int[A.length];
        double el = 1;
        double r = 1;
        for(int i = 0;i<A.length;i++){
            mul*=A[i]%(Math.pow(10,9)+7);
        }
        System.out.println((int)mul);


        for(int j = 0;j<A.length;j++){
            el = mul/A[j];
            r = el%(Math.pow(10,9)+7);
            //res[j]= (int)(((double)(mul/A[j]))%(Math.pow(10,9)+7)) ;
            res[j] = (int)(r);
        }

        return res;

    }
    public void printarr(int[]A){
        for (int x: A
             ) {
            System.out.println(x);
        }
        System.out.println("Above is the result");
    }

    public int specialIndex(int[]A){
        int res = 0, l = A.length, countEven = 0, countOdd = 0;
        int[] even = new int[l], odd = new int[l];
        for(int i = 0;i<l;i++){
            if(i%2 ==0){
                countEven+=A[i];
            }
            else{
                countOdd += A[i];
            }
            even[i] = countEven;
            odd[i] = countOdd;
        }

        return res;
    }
}

class HelloWorld {
    public static void main(String[] args) {

        Solution2 sol = new Solution2();


//        sol.solve(new int[]{1,2,3},1,3);
//        sol.count1("111011101");
//        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,132,756,459,533,219,48,679,680,935,384,520,831,35,54,530,672,8,384,67,418,687,589,931,847,527,92,654,416,702,911,763,263,48,737,329,633,757,992,366,248,983,723,754,652,73,632,885,273,437,767,478,238,275,360,167,487,898,910,61,905,505,517,320,987,494,267,91,948,74,501,385,278,914,530,465,941,51,762,771,828,126,16,689,869,630,737,726,1000,889,234,307,352,514,592,846,413,842,270,416));
//        int[] arr1 = new int[]{1,132,756,459,533,219,48,679,680,935,384,520,831,35,54,530,672,8,384,67,418,687,589,931,847,527,92,654,416,702,911,763,263,48,737,329,633,757,992,366,248,983,723,754,652,73,632,885,273,437,767,478,238,275,360,167,487,898,910,61,905,505,517,320,987,494,267,91,948,74,501,385,278,914,530,465,941,51,762,771,828,126,16,689,869,630,737,726,1000,889,234,307,352,514,592,846,413,842,270,416};
//        sol.prod(arr1);
        sol.specialIndex(new int[]{2,1,6,4});



    }

}


