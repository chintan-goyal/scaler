package assignments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DayGraph1 {
    public int inf = Integer.MAX_VALUE / 2;


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

    //        public int solve(int A, int[][] B) {
//            HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
//            for(int[] x: B){
//                int start = x[0];
//                int end = x[1];
//                if(hm.containsKey(start)) hm.get(start).add(end);
//                else hm.put(start, new ArrayList<>(Arrays.asList(end)));
//                hm.put(start, new ArrayList<>(Arrays.asList(end)));
//            }
//            boolean[] visited = new boolean[A+1];
//            Arrays.fill(visited,false);
//            boolean[] path = new boolean[A+1];
//            Arrays.fill(path,false);
//            int ans = 0;
//            for(int i = 1;i<=A;i++){
//                if(!visited[i]){
//                    if(checkCycle(visited, path, i,hm)) return 1;
//                }
//            }
//
//            return ans;
//        }
    public int solve(int[][] A, int[] B, int[] C) {
        int l = A.length;
        int m = A[0].length;
//        boolean[][] visited = new boolean[l + 1][m + 1];
//        for (boolean[] x : visited) Arrays.fill(x, false);
        boolean[][] path = new boolean[l ][m ];
        for (boolean[] x : path) Arrays.fill(x, false);
        path[B[0]][B[1]] =true;
        int count = Integer.MAX_VALUE/2;
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dir) {
            int nr = B[0]+d[0];
            int nc = B[1]+d[1];
            if(nr>=0 && nc>=0 && nr<l && nc <m && A[nr][nc] != 1 && !path[nr][nc]){
                int res = 1 + helper(new int[]{nr,nc},C,path,A,d);
                count = Math.min(count, res);
            }
        }
        return count == inf? -1 : count;
    }
    public int helper(int[] src, int[] dest,boolean[][] path, int[][] A,int[]dr) {
        int l = A.length;
        int m = A[0].length;
        int count = inf;
        path[src[0]][src[1]] = true;

        int nr = src[0] + dr[0];
        int nc = src[1] + dr[1];
        if(nr>=0 && nc>=0 && nr<l && nc <m && A[nr][nc] != 1 && !path[nr][nc])  {
            path[src[0]][src[1]] = false;
            return Math.min(count, 1+  helper(new int[]{nr,nc},dest,path,A,dr));
        }
        if (src[0] == dest[0] && dest[1] == src[1]) {
                path[src[0]][src[1]] = false;
                return 0;
        }


        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dir) {
            if(d[0] != dr[0] && d[1] != dr[1] && d[0] != (-1) * dr[0] && d[1] != (-1) * dr[1]){
                nr = src[0]+d[0];
                nc = src[1]+d[1];
                if(nr>=0 && nc>=0 && nr<l && nc <m && A[nr][nc] != 1 && !path[nr][nc]){
                    int res = 1 + helper(new int[]{nr,nc},dest,path,A,d);
                    count = Math.min(count, res);
                }
            }
        }
        path[src[0]][src[1]] = false;
        return count;
    }

//    public int helper(int[] src, int[] dest, boolean[][] visited,boolean[][] path, int[][] A) {
//        if (src[0] == dest[0] && dest[1] == src[1]) return 0;
//        int l = A.length;
//        int m = A[0].length;
//        int count = inf;
//        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//        path[src[0]][src[1]] = true;
//
//        for (int[] d : dir) {
//            int r = src[0], c = src[1];
//            int tempCount = 0;
//            while (r >= 0 && c >= 0 && r < l && c < m && A[r][c] != 1) {
//                tempCount += 1;
//                r += d[0];
//                c += d[1];
//            }
//            r -= d[0];
//            c -= d[1];
//            tempCount -= 1;
//
//            if (!visited[r][c] && tempCount > 0 && !path[r][c]) {
//                int res = tempCount + helper(new int[]{r, c}, dest, visited, path,A);
//                count = Math.min(res, count);
//            }
//
//        }
//        path[src[0]][src[1]] = false;
//        visited[src[0]][src[1]] = true;
//        return count ;
//    }

    private boolean checkCycle(boolean[] visited, boolean[] path, int i, HashMap<Integer, ArrayList<Integer>> hm) {
        visited[i] = true;
        if (path[i]) return true;
        path[i] = true;
        if (hm.containsKey(i)) {
            for (int x : hm.get(i)) {
                if (path[x]) return true;
                if (!visited[x]) {
                    if (checkCycle(visited, path, x, hm)) return true;
                    else path[x] = false;
                }
            }
        }
        path[i] = false;
        return false;
    }


    public static void main(String[] args) {
        DayGraph1 s = new DayGraph1();
        ArrayList<Integer[]> ab = new ArrayList<>();
        int[][] a = {{1, 2, 3}, {2, 4, 5}};
//        for (int[] x : a) for (int i : x) System.out.println(i);
        //[[1,1,0,1],[0,0,0,1],[1,0,0,1],[0,0,1,0]]
//        System.out.println(s.solve(
//                new int[][]{{1,1,0,1},{0,0,0,1},{1,0,0,1},{0,0,1,0}},
//                new int[]{1,1},
//                new int[]{2,1}
//        ));
        System.out.println(s.solve(
                new int[][]{{0,0},{0,0}},
                new int[]{0,0},
                new int[]{0,1}
        ));
    }
    public int addTwoNumbers(int a, int b){
        return a+b;
    }

}
