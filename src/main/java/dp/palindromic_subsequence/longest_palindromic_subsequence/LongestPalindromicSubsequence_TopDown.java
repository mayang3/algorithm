package dp.palindromic_subsequence.longest_palindromic_subsequence;

/**
 * @author neo82
 */
public class LongestPalindromicSubsequence_TopDown {
	public static void main(String[] args) {
		String s = "abdbca";
		Integer [][] dp = new Integer[s.length()][s.length()];

		LongestPalindromicSubsequence_TopDown lps = new LongestPalindromicSubsequence_TopDown();
		System.out.println(lps.findLPSLength(dp, s, 0, s.length()-1));
	}

	private int findLPSLength(Integer[][] dp, String s, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return 0;
		} else if (startIndex == endIndex) {
			return 1;
		}

		if (dp[startIndex][endIndex] != null) {
			return dp[startIndex][endIndex];
		}

		if (s.charAt(startIndex) == s.charAt(endIndex)) {
			return dp[startIndex][endIndex] = 2 + findLPSLength(dp, s, startIndex+1, endIndex-1);
		}

		int len1 = findLPSLength(dp, s, startIndex+1, endIndex);
		int len2 = findLPSLength(dp, s, startIndex, endIndex-1);

		return dp[startIndex][endIndex] = Math.max(len1, len2);
	}
}
