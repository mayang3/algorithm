package string;

import java.util.Arrays;

public class KMP_partialMatch {

    public int [] getPartialMatchNaive1(String pattern) {
        int [] pi = new int[pattern.length()];

        for (int i = 1; i <= pattern.length(); i++) {
            String pre1 = pattern.substring(0, i);

            for (int j = 0; j < pre1.length(); j++) { // i 와 같은 길이는 치지 않는다.
                String pre2 = pre1.substring(0, j);


                boolean success = true;
                int l = pre1.length()-1;

                for (int k = pre2.length()-1; k >=0 ; k--) {
                    if (pre2.charAt(k) != pre1.charAt(l--)) {
                        success = false;
                        break;
                    }
                }

                if (success) {
                    pi[i-1] = Math.max(pi[i-1], pre2.length());
                }
            }
        }

        return pi;
    }

    public int [] getPartialMatchNaive2(String pattern) {
        int [] pi = new int[pattern.length()];
        int n = pattern.length();

        // 단순한 문자열 검색 알고리즘을 구현한다.
        // begin 이 기준 문자열, i 가 비교대상 문자열
        for (int begin = 1; begin < n; begin++) {
            for (int i = 0; i + begin < n; i++) {
                 if (pattern.charAt(begin+i) != pattern.charAt(i)) {
                     break;
                 }

                 // i+1 번째 글자가 대응되었다.
                pi[begin + i] = Math.max(pi[begin + i], i+1);
            }
        }

        return pi;
    }

    public int [] getPartialMatch(String pattern) {
        int N = pattern.length();

        int [] pi = new int[N];

        // KMP 로 자기 자신을 찾는다.
        // N 을 N 에서 찾는다. begin = 0 이면 자기 자신을 찾아버려서 안됨
        int begin = 1;
        int matched = 0;

        // 비교할 문자가 N 의 끝에 도달할 때까지 찾으면서 부분 일치를 모두 기록한다.
        while (begin + matched < N) {
            if (pattern.charAt(begin + matched) == pattern.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched-1];
                    matched = pi[matched - 1];
                }
            }
        }

        return pi;
    }

    public static void main(String[] args) {
        KMP_partialMatch kmp_partialMatch = new KMP_partialMatch();
        System.out.println(Arrays.toString(kmp_partialMatch.getPartialMatchNaive1("AABAAB")));
        System.out.println(Arrays.toString(kmp_partialMatch.getPartialMatchNaive2("AABAAB")));
        System.out.println(Arrays.toString(kmp_partialMatch.getPartialMatch("AABAAB")));
    }
}
