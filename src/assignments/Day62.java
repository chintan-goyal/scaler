package assignments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }
}
public class Day62 {

    //  Definition for binary tree



        public TreeNode buildTree(int[] A, int[] B) {
            int l = A.length;
            HashMap<Integer, Integer> dic = new HashMap<>();
            for(int i = 0;i<l;i++) dic.put(A[i],i);
            return buildTreeHelper(A,B,0,l-1,0,l-1,dic);

        }

    private TreeNode buildTreeHelper(int[] A, int[] B, int is, int ie, int ps, int pe, HashMap<Integer, Integer> dic) {
        int il = ie-is +1;
        int pl = pe-ps+1;
        if(il <= 0 || pl <= 0 ) return null;
        if(il == 1) return new TreeNode(A[is]);
        TreeNode root = new TreeNode(B[pe]);
        int idxRootInorder = dic.get(B[pe]);
        int leftIs= is, leftIe= idxRootInorder -1, rightIs= leftIe+2, rightIe=ie;
        int ll=leftIe-leftIs+1, lr = rightIe-rightIs+1;
        int leftPs, leftPe, rightPs, rightPe ;
        if(ll == 0){
            leftPs = -1;
            leftPe = -2;
        }
        else{
            leftPs= ps;
            leftPe=leftPs+ll-1;
        }
        if(lr == 0){
            rightPs = -1;
            rightPe = -2;
        }
        else{
            rightPs= leftPe+1;
            rightPe = rightPs+lr-1;
        }
        root.left = buildTreeHelper(A, B, leftIs,leftIe, leftPs, leftPe, dic);
        root.right = buildTreeHelper(A, B, rightIs, rightIe, rightPs, rightPe, dic);

        return root;
    }
    public  int[] solve(TreeNode A) {
        Queue<TreeNode> q = new PriorityQueue<>();
        ArrayList<Integer> arr = new ArrayList<>();
        q.add(A);
        while(q.peek() != null){
            for(int i = 0;i<q.size();i++){
                if(i == q.size()-1) arr.add(q.peek().val);
                TreeNode n = q.remove();
                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
            }
        }
        int l = arr.size();
        int[] res = new int[l];
        for(int i = 0;i<l;i++)
        {
            res[i] = arr.get(i);
        }
        return res;
    }


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


    public static void main(String[] args) {
        Day62 s = new Day62();
        StringBuilder a = new StringBuilder();


    }
}
