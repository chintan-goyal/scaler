package assignments;

import java.util.ArrayList;
import java.util.Arrays;

public class Day40 {
    public void generateParenthesisHelper(ArrayList<String> res, int open_cnt, String str, int l) {
        int s = str.length();
        if (s == l & open_cnt == 0) res.add(str);
        else if (s < l) {
            if (open_cnt >= 0) {
                generateParenthesisHelper(res, open_cnt + 1, str + "(", l - 1);
                generateParenthesisHelper(res, open_cnt - 1, str + "(", l - 1);
            } else {
                generateParenthesisHelper(res, open_cnt - 1, str + "(", l - 1);
            }
        }
    }
    public ArrayList<String> generateParenthesis(int A) {
        ArrayList<String> res = new ArrayList<>();
        generateParenthesisHelper(res, 0, "", 2 * A);
        return res;
    }


    public void towerofhanoiHelper(int A, int s, int e, int h, int size, ArrayList<ArrayList<Integer>> res) {
        if (size == 1) {
            res.add(new ArrayList<Integer>(Arrays.asList(A, s, e)));
            return;
        } else if (size == 0) return;
        else if (size == 2) {
            res.add(new ArrayList<>(Arrays.asList(A, s, h)));
            res.add(new ArrayList<>(Arrays.asList(A + 1, s, e)));
            res.add(new ArrayList<>(Arrays.asList(A, h, e)));
            return;
        } else {
            towerofhanoiHelper(A, s, h, e, size - 1, res);
            res.add(new ArrayList<Integer>(Arrays.asList(size, s, e)));
            towerofhanoiHelper(A, h, e, s, size - 1, res);

        }
    }

    public ArrayList<ArrayList<Integer>> towerOfHanoi(int A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        towerofhanoiHelper(1, 1, 3, 2, A, res);
        return res;

    }

    public static void main(String[] args) {
        Day40 s = new Day40();
        s.towerOfHanoi(2);
    }
}
