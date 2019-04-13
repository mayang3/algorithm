package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class LongestPalindromicSubstring_bruteForce {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		while (n-- > 0) {
			String s = scanner.next();

			System.out.println(solve(s));
		}
	}

	/**
	 * 모든 substring 마다 palindrome 인지를 확인한다.
	 *
	 * time complexity : O(n^3)
	 *
	 * @param s
	 * @return
	 */
	private static String solve(String s) {

		List<String> ret = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {

				int l = i;
				int r = j;

				boolean palindrome = true;

				while (l <= r) {
					if (s.charAt(l) != s.charAt(r)) {
						palindrome = false;
					}

					l++;
					r--;
				}

				if (palindrome) {
					ret.add(s.substring(i, j+1));
				}
			}
		}

		Collections.sort(ret, (o1, o2) -> o2.length() - o1.length());

		return ret.get(0);
	}
}
