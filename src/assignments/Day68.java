package assignments;

import java.util.LinkedList;
import java.util.Queue;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}


public class Day68 {
    public int[] getDiameter(TreeNode root) {
        if (root == null) return new int[]{-1, -1};
        int[] left_data = getDiameter(root.left);
        int[] right_data = getDiameter(root.right);
        int height = 1 + Math.max(left_data[0], right_data[0]);
        int diameter = 2 + left_data[0] + right_data[0];
        return new int[]{height,
                left_data[1] >= right_data[1] ? Math.max(diameter, left_data[1]) : Math.max(right_data[1], diameter)
        };
    }

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                TreeLinkNode el = q.remove();
                el.next = i != s - 1 ? q.peek() : null;
                if (el.left != null) q.add(el.left);
                if (el.right != null) q.add(el.right);
            }

        }
    }

    public int getSum(TreeNode A) {
        if (A == null) return 0;
        return A.val + getSum(A.left) + getSum(A.right);
    }

    public int[] checkSum(TreeNode A, int sum) {
        if(A == null) return new int[]{0,0};
        int[] left = checkSum(A.left, sum);
        int[] right = checkSum(A.right,sum);
        int[] res = new int[2];
        int s = A.val+left[0]+right[0];
        res[0] = s;
        res[1] = 0;
        if(s == sum || left[0] == sum || right[0] == sum || left[1] == 1 || right[1] == 1) res[1] =1;
        return res;

    }


    public int checkSumation(TreeNode A) {
        int tot = getSum(A);
        if(tot%2 != 0) return 0;
        return checkSum(A,tot/2)[1];

    }

    public static void main(String[] args) {
    }
}
