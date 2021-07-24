package string;

import java.util.*;

public class SuffixArrayNaive {

    static List<Integer> getSuffixArrayNaive(String s) {
        List<Integer> perm = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            perm.add(i);
        }

        // 두 문자열을 비교하는데 최대 두 문자열의 길이에 비례하는 시간이 걸리므로, 한 번 비교에 O(n) 시간이 걸린다고 할 수 있다.
        // Total Time Complexity : O(N^2logN)
        Collections.sort(perm, Comparator.comparing(s::substring));

        return perm;
    }

    public static void main(String[] args) {
        List<Integer> alohomora = getSuffixArrayNaive("alohomora");
        System.out.println(alohomora);
    }
}
