package assignments;

import java.util.*;


public class Day66 {
    public TreeNode sortedArrayToBST(final int[] A) {

        return helper(A, 0, A.length - 1);
    }

    private TreeNode helper(int[] a, int s, int e) {
        int l = e - s + 1, m = s+ l / 2, ls = s, le = m - 1, rs = m +1, re = e;
        if ( l <= 0) return  null;
        else if (l ==1 ) return new TreeNode(a[s]);
        else {
            TreeNode res = new TreeNode(a[m]);
            res.left = helper(a, ls, le);
            res.right = helper(a, rs, re);
            return res;
        }


        }
    public TreeNode deleteNode(TreeNode A, int B) {
        TreeNode parent = A;
        TreeNode child = A;
        int d;
        String whichChild = "False";
        while (child != null){
            d = child.val;
            if(d>B) {
                parent = child;
                child = child.left;
                whichChild = "left";
            }
            else if(d<B){
                parent = child;
                child = child.right;
                whichChild = "right";
            }
            else {
                
            };
        }


        //if it is a leaf node.
        if(child.left == null && child.right == null) {
            if(whichChild == "left") parent.left = null;
            else parent.right = null;
        }

        //if one of the childern is null;
        else if(child.left == null || child.right == null) {
            if (child.left == null) {
                if (whichChild == "left") parent.left = child.right;
                else parent.right = child.right;
            } else {
                if (whichChild == "left") parent.left = child.left;
                else parent.right = child.left;
            }
        }

        //both the children are not null
        else{
            //get the highest of left subtree
            int leftHigh = child.left.val;
            TreeNode leftNode = child.left;
            while(leftNode != null){
                leftHigh = leftNode.val;
                leftNode = leftNode.right;
            }
            child = deleteNode(child,leftHigh);
            child.val = leftHigh;
        }
        return A;
    }
    // Function to insert nodes in level order
    public TreeNode insertLevelOrder(int[] arr, TreeNode root, int i) {
        // Base case for recursion
        if (i < arr.length) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
        }
        return root;
    }
    public static TreeNode createTree(String input) {
        String[] parts = input.split(" ");
        int index = 0;

        int rootVal = Integer.parseInt(parts[index]);
        if (rootVal == -1) return null;

        TreeNode root = new TreeNode(rootVal);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty() && index < parts.length - 1) {
            TreeNode current = queue.poll();

            // Process left child
            index++;
            int leftChildVal = Integer.parseInt(parts[index]);
            if (leftChildVal != -1) {
                current.left = new TreeNode(leftChildVal);
                queue.add(current.left);
            }

            // Ensure there are still elements to process
            if (index >= parts.length - 1) break;

            // Process right child
            index++;
            int rightChildVal = Integer.parseInt(parts[index]);
            if (rightChildVal != -1) {
                current.right = new TreeNode(rightChildVal);
                queue.add(current.right);
            }
        }

        return root;
    }

    public static void printTree(TreeNode node) {
        if(node == null)
            return;

        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }
    //  Definition for binary tree


    public static void main(String[] args) {
        Day66 s = new Day66();
        StringBuilder a = new StringBuilder();

//        s.sortedArrayToBST(new int[]{1,2,3,5,10});
        String input="6 9 4 -1 8 -1 3";
        TreeNode root=createTree(input);
        System.out.println("Binary Tree:");
        printTree(root);

//        s.insertLevelOrder(arr,)
//        s.root = t2.insertLevelOrder(arr, t2.root, 0);

    }
}
