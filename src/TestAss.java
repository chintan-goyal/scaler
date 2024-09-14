import java.io.*;

public class TestAss {

        public static void main (String[]args) throws IOException {
            System.out.println("hi");
            FileWriter fw = new FileWriter("/Users/chintan/Personal/Learning/repos/scaler/src/test.txt",false);


            PrintWriter pw = new PrintWriter(fw,false);
            pw.println("hello dear");
            pw.println("this is hey");
            pw.append("what's up now");
//            pw.flush();
//            fw.flush();

            pw.println("What's up");
            pw.close();
            fw.close();

        }


    public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int m = A.length,n = A[0].length, q = C.length, uli,ulj,bri,brj;
        long sum = 0,ans = 0, mod = (long) Math.pow(10,9) +7;
        long[][] prefSum = new long[m][n];
        int[] res = new int[B.length];


        for(int i =0;i<m;i++){
            for(int j = 0;j<n;j++){
                sum += A[i][j];
                prefSum[i][j] = sum;
            }
            sum = 0;
        }
        sum = 0;
        for(int j =0;j<n;j++){
            for(int i = 0;i<m;i++){
                sum += prefSum[i][j];
                prefSum[i][j] = sum;
            }
            sum = 0;
        }

        for(int k = 0 ; k<q;k++){
            uli = B[k]-1;
            ulj = C[k]-1;
            bri = D[k]-1;
            brj = E[k]-1;
            ans = prefSum[bri][brj];
            ans = ulj == 0 ? ans : ans - prefSum[bri][ulj-1];
            ans = uli == 0 ? ans : ans - prefSum[uli -1][brj];
            ans = (uli != 0 & ulj != 0 ) ? ans + prefSum[uli-1][ulj-1] : ans;

            ans = ans%mod;
            res[k] = (int) ((ans + mod)%(Math.pow(10,9)+7));
        }

        return res;
    }


}
