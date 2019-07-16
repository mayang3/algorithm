package dp.palindromic_subsequence.longest_palindromic_subsequence;

/**
 * @author neo82
 */
public class LongestPalindromicSubsequence_BottomUp {
	public static void main(String[] args) {
		LongestPalindromicSubsequence_BottomUp bottomUp = new LongestPalindromicSubsequence_BottomUp();
		int len = bottomUp.findLPSLength("cddpd");

		System.out.println(len);
	}

	private int findLPSLength(String s) {
		int n = s.length();

		int [][] dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}

		for (int startIndex = n; startIndex >= 0; startIndex--) {
			for (int endIndex = startIndex+1; endIndex < n; endIndex++) {
				if (s.charAt(startIndex) == s.charAt(endIndex)) {
					dp[startIndex][endIndex] = 2 + dp[startIndex+1][endIndex-1];
				} else {
					dp[startIndex][endIndex] = Math.max(dp[startIndex+1][endIndex], dp[startIndex][endIndex-1]);
				}
			}
		}

		return dp[0][s.length()-1];
	}
}
