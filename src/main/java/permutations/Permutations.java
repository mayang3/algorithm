package permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author neo82
 */
public class Permutations {

    public static void main(String[] args) {
        List<Integer> P = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            P.add(i);
        }

        do {
            System.out.println(P);
        } while (nextPermutations(P));
    }


    static boolean nextPermutations(List<Integer> P) {
        int i = P.size() - 2;

        // 중간에 평지가 있는 감소하는 부분수열 또한, 사전순으로 더 이상 다음 순열을 만들수 없다. -> 즉, 마지막 순열이다.
        while (i >= 0 && P.get(i) >= P.get(i + 1)) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = P.size() - 1;

        // 같은 값은 바꿔도 다음 순열이 완성되지 않으므로 ..
        while (P.get(i) >= P.get(j)) {
            j--;
        }

        Collections.swap(P, i, j);
        // ArrayList 의 subList 는 새로운 object 가 아닌, ArrayList 의 view 를 반환한다.
        // Set 의 subSet 이나 map 의 subMap 도 같은 원리로써 동작한다.
        Collections.reverse(P.subList(i + 1, P.size()));

        return true;
    }
}
