package assignments;

import java.util.*;

public class Day67 {


    public boolean getPath(TreeNode root, int a, ArrayList<Integer> arr) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return false;
        if (root.val == a || getPath(root.left, a, arr) || getPath(root.right, a, arr)) {
            arr.add(root.val);
            return true;
        }
        return false;
    }


    public int lca(TreeNode A, int B, int C) {
        ArrayList<Integer> bPath = new ArrayList<>();
        ArrayList<Integer> cPath = new ArrayList<>();
        getPath(A, B, bPath);
        getPath(A, C, cPath);
        int count = 0;
        int l = bPath.size();
        int m = cPath.size();
        while (bPath.get(l - 1 - count) == cPath.get(l - 1 - count)) {
            count++;
        }
        return bPath.get(bPath.size() - count);

    }

    public TreeNode getRightMost(TreeNode a) {
        TreeNode temp = a;
        temp = temp.left;
        if (temp.right != null ) {
            while (temp.right != null && temp.right != a) temp = temp.right;
            return temp;
        } else return temp;
    }

    public ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode curr = A;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode r = getRightMost(curr);
//                System.out.println(curr.val + " " +  r.val);
                if (r.right == null) {
                    r.right = curr;
                    curr = curr.left;}
                else {
                    res.add(curr.val);
                    curr = curr.right;
                }

            } else {
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }

    public int solve(int[] A, int[] B, int C) {
        float value = 0;
        int l = A.length;
        float[][] fr = new float[l][2];
        for(int i = 0;i<l;i++){
            fr[i][0] = (float)A[i]/B[i];
            fr[i][1] = i;
        }
        Arrays.sort(fr, (o1, o2) -> o2[0]>o1[0] ? 1 : 0);

        for(float[] x :fr) System.out.println(x[0] + " " + x[1]);
        for(int i = 0;i<l;i++){
            int idx = (int) fr[i][1];
            if(C<=0) break;

            else if(C >= B[idx]){
                value += A[idx];
                C -= B[idx];
            }
            else{
                value += (float)A[idx]*(float)((float)C/B[idx]);
                // System.out.println(value);
                break;
            }

        }
        return (int) (value*100);
    }
//    public int lca(TreeNode A, int B, int C) {
//        int count = 0;
//        HashMap<TreeNode, Integer[]> hm = new HashMap<>();
//        TreeNode root = A;
//        getEntryExit(A,count,hm);
//        for(Map.Entry<TreeNode,Integer[]> x : hm.entrySet()) System.out.println(x.getKey() + " " + hm.get(x.getKey())[0]+ " " + hm.get(x.getKey())[1]);
//        return 0;
//
//    }
//
//    private int getEntryExit(TreeNode A, int count, HashMap<TreeNode, Integer[]> hm) {
//        if (A == null) return count;
//        Integer[] finalCount = new Integer[2];
//        finalCount[0] = count;
//        hm.put(A,finalCount);
//        count++;
//        int leftCount = getEntryExit(A.left,count,hm);
//        int newCount = getEntryExit(A.right,leftCount,hm);
//        hm.get(A)[1] = newCount;
//        count++;
//        return count;
//    }

    public static void main(String[] args) {
        Day67 s = new Day67();
        TreeNode a = Day66.createTree("6 9 4 -1 -1 8 -1 -1 3 -1 -1");
        ArrayList<Integer> ans = s.solve(a);
        for(int x:ans) System.out.println(x);


    }
}

/*
12158
126797
146802
82203
133023
124244
109794
120461
4697
19763
149677
41460
7809
127678
60937
54310
16533
21629
150487
75941
97231
112639
9056
49144
156383
gap
42151
142908
137081
60849
80641
77702
146268
140155
94124
12844
128094
37682
58504
76320
115775
77802
64159
60801
16928
54161
156383
 */
