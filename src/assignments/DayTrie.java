package assignments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    Node[] children;
    boolean isEnd;
    ArrayList<String[]> dic;

    public Node() {
        children = new Node[26];
        isEnd = false;
        dic = new ArrayList<>();
    }
}

public class DayTrie {
    public static void insert(Node root, String word, int weight) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (curr.children[c] == null) curr.children[c] = new Node();
            curr = curr.children[c];
            curr.dic.add(new String[]{word, String.valueOf(weight)});

        }
        curr.isEnd = true;
    }

    public static void printAutoComplete(Node root, String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            curr = curr.children[c];
        }
        if (curr == null || curr.dic.size() == 0) System.out.println(-1);
        else {
            curr.dic.sort(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return Integer.parseInt(o2[1])-Integer.parseInt(o1[1]);
                }
            });
            for (int x = 0; x < 5; x++) {
                if(x <= curr.dic.size()-1) System.out.print(curr.dic.get(x)[0] + " ");

            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());

        for (int x = 0; x < cases; x++) {
            Node root = new Node();
            String size = sc.nextLine();
            int n = Integer.parseInt(size.split(" ")[0]);
            int m = Integer.parseInt(size.split(" ")[1]);
            String[] dic = sc.nextLine().split(" ");
            String[] weights = sc.nextLine().split(" ");
            String[] words = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                insert(root, dic[i], Integer.parseInt(weights[i]));
            }
            for (int i = 0; i < m - 1; i++) {
                printAutoComplete(root, words[i]);
                System.out.println();
            }
            printAutoComplete(root, words[m - 1]);
            System.out.println();
        }


    }
}
