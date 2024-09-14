package assignments;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Day61 {


    public int[] slidingMaximum(final int[] A, int B) {
        int l = A.length;
        if (B==1) return A;
        int[] res = new int[l-B+1];
        Deque<Integer> win = new LinkedList<>();
        for(int i = 0;i<B;i++){
            while(!win.isEmpty() && A[win.getLast()] < A[i]){
                win.removeLast();
            }
//            win.addLast(i);
        }
        for(int i = B;i<l;i++){
            if(win.getFirst() + B > i){
                while(!win.isEmpty() && A[win.getLast()] < A[i]){
                    win.removeLast();
                }
                win.addLast(i);
            }
            else{
                win.removeFirst();
                while(!win.isEmpty() && A[win.getLast()] < A[i]){
                    win.removeLast();
                }
                win.addLast(i);
            }
            res[i-B] = A[win.getFirst()];
        }
        return res;
    }

    public int isValidBST(TreeNode A) {
        return  validator(A, Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
    }

    public int[] validator(TreeNode A, int min, int max){
        if(A == null) return new int[]{1,0};
        int val = A.val;
        int[] left = validator(A.left,min,val-1);
        int[] right = validator(A.right,val+1,max);
        if(left[0] == 0 || right[0]==0 ){
            return new int[]{0,Math.max(left[1],right[1])};
        }
        else{
            if (val < min || val>max){
                int left_val = A.left == null ? Integer.MIN_VALUE:A.left.val;
                int right_val = A.right == null ? Integer.MAX_VALUE: A.right.val;
                if (val>left_val && val<right_val) return new int[]{0,1+left[1]+right[1]};
                else  return new int[]{0,Math.max(left[1],right[1])};
            }
            else return new int[]{1,1+left[1]+right[1]};
        }
    }
    public void printTree(TreeNode root, int space) {
        int COUNT = 5; // Adjust for spacing

        if (root == null) {
            return;
        }

        space += COUNT;
        printTree(root.right, space);

        System.out.println();
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(root.val + "\n"); // Removed branches for now

        printTree(root.left, space);
    }
    public void printPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
    public void printPostOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.val + " ");
    }
    public TreeNode createTree(String input) {
        // Handle potential errors
        if (input == null || input.isEmpty()) {
            return null;
        }

        String[] values = input.split(" ");
        Queue<TreeNode> queue = new LinkedList<>();

        // Create the root node
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode current = queue.poll();

            // Create and attach left child
            String leftVal = values[index];
            if (!leftVal.equals("-1")) {
                TreeNode leftChild = new TreeNode(Integer.parseInt(leftVal));
                current.left = leftChild;
                queue.offer(leftChild);
            }
            index++;

            // Create and attach right child
            String rightVal = values[index];
            if (!rightVal.equals("-1")) {
                TreeNode rightChild = new TreeNode(Integer.parseInt(rightVal));
                current.right = rightChild;
                queue.offer(rightChild);
            }
            index++;
        }

        return root;
    }





    public static void main(String[] args) {
        Day61 s = new Day61();
        String input = "6 9 4 -1 -1 8 -1 -1 3 -1 -1";
        TreeNode treeRoot = s.createTree(input);
        s.printPreOrder(treeRoot);
        System.out.println();
        s.printPostOrder(treeRoot);

//        StringBuilder a = new StringBuilder();


    }
}
