package dp;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class LongestPalindromicSubstring_dp_bottomUp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		while (n-- > 0) {
			String s = scanner.next();

			System.out.println(solve(s));
		}
	}

	private static String solve(String s) {
		int n = s.length();

		// dp table
		boolean [][] table = new boolean[n][n];
		// 여태까지 발견된 가장 긴 회문의 길이
		int maxLength = 1;

		// 길이 1짜리 모든 string 은 palindrome 이다.
		for (int i = 0; i < n; i++) {
			table[i][i] = true;
		}

		// 길이 2의 subString 을 체크한다.
		int start = 0;

		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i+1)) {
				table[i][i+1] = true;
				if (maxLength < 2) {
					start = i;
					maxLength = 2;
				}
			}
		}

		// 2 보다 긴 길이를 가진 subString 을 체크한다.
		// k : subString length
		for (int k = 3; k <= n; k++) {
			// i : 시작 index
			for (int i = 0; i < n - k + 1; i++) {
				// j : end index (start index + length - 1)
				int j = i + k - 1;

				// i ~ j 하위 범위의 string 이 palindrome 이고, i,j 의 char 가 같으면 현재 위치도 palindrome
				if (table[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
					table[i][j] = true;

					if (k > maxLength) {
						start = i;
						maxLength = k;
					}
				}
			}
		}

		return s.substring(start, start + maxLength);
	}
}
