package string;

public class KMP {
	/**
	 *
	 * @param pat 바늘
	 * @param txt 짚더미
	 */
	static void kmpSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int [] lps = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[] array)
		// lps[i] = pat[0..i] 까지의 문자열중 prefix = suffix  가 되는 최대 길이
		// prefix = 0 부터 suffix = length-1 부터 항상 시작해야 한다.
		// 이렇게 suffix 를 이용하는 이유는, 문자열 검색에서 전체 문자열이 매칭되었다고 해서 그 문자열이 걸러지는 것이 아니라,
		// 매칭된 문자열의 부분 문자열이 다음 문자열의 부분집합이 될 수도 있기 때문이다.
		computeLPSArray(pat, M, lps);

		int i = 0; // index for txt[]
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}

			// M : 패턴의 길이 -> 패턴을 전부 다 찾은 경우...
			if (j == M) {
				System.out.println("Found Pattern at index " + (i-j));
				j = lps[j-1]; // lps[j-1] 만큼 건너뛴다.
			} else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				// 패턴을 다 찾지 못했고, 현재 찾고 있는 패턴의 문자가 일치하지 않는 경우,

				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0) {
					// j 에서 불일치가 발생했고, j-1 까지는 match 된 상태이기 때문에, lps array 를 볼때 j-1 은 일치될 수 있다는 가능성을 보고, j-1 을 참조한다.
					j = lps[j-1]; // lps[j-1] 만큼 건너 뛴다.
				} else {
					i = i+1; //
				}
			}
		}

	}

	/**
	 *
	 * @param pat
	 * @param M 바늘의 길이. pattern.length
	 * @param lps
	 */
	static void computeLPSArray(String pat, int M, int [] lps) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i=1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				// (pat[i] != pat[len])

				// this is tricky. Consider the example.
				// AAACAAAA and i=7. The idea is similar to search step.
				if (len != 0) {
					// 현재 위치에서 불일치 한경우 현재 대응된 길이를 pi[len-1] 로 줄인다.
					// -> 현재 위치에서 불일치 했기 때문에, 현재까지 최대 대응된 길이는 pi[len-1] 이다.
					// -> 접두사와 접미사가 같기 때문에 무조건 앞에서부터 잘라서 보면 pi[0] ~ pi[len-1] 이 현재까지 대응된 최대 길이가 된다.
					len = lps[len-1];
					// Also, note that we do not increment "i" here.
				} else { // if (len == 0)
					i++;
				}
			}
		}
	}

	public static void main(String[] args) {
		String txt = "AABAACAABC";
		String pat = "AABAAA";

		kmpSearch(pat, txt);
	}

}
