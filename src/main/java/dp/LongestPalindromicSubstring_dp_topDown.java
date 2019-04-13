package dp;

import java.util.Scanner;

/**
 * base case 를 특정할 수 있으므로 top down 방식도 가능할것 같아 짜봄 -> accept in geeksforgeeks
 */
public class LongestPalindromicSubstring_dp_topDown {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		while (n-- > 0) {
			String s = scanner.next();

			String [][] dp = new String[s.length()][s.length()];
			Palindrome palindrome = new Palindrome();

			String ret = "";

			for (int i = 0; i < s.length(); i++) {
				for (int j = i; j < s.length(); j++) {
					String solve = palindrome.solve(dp, s, i, j);

					if (solve.length() > ret.length()) {
						ret = solve;
					}
				}

			}

			System.out.println(ret);
		}

	}

	static class Palindrome {
		String solve(String [][] dp, String s, int start, int end) {
			if (start == end) {
				return String.valueOf(s.charAt(start));
			} else if (end - start == 1) {
				if (s.charAt(start) == s.charAt(end)) {
					return String.valueOf(s.charAt(start)) + s.charAt(end);
				} else {
					return "";
				}
			}

			if (dp[start][end] != null) {
				return dp[start][end];
			}

			if (s.charAt(start) != s.charAt(end)) {
				return "";
			}

			String ss = solve(dp, s, start+1, end-1);

			return dp[start][end] = (ss.length() > 0 ? s.charAt(start) + ss + s.charAt(end) : "");
		}
	}


}
