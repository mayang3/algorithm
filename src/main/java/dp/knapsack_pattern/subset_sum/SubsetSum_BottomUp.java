package dp.knapsack_pattern.subset_sum;

public class SubsetSum_BottomUp {

	public static boolean solve(int [] num, int S) {
		int n = num.length;

		boolean [][] dp = new boolean[n][S+1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		for (int s = 1; s <= S; s++) {
			if (num[0] == s) {
				dp[0][s] = true;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int s = 1; s <= S; s++) {
				if (dp[i-1][s]) {
					dp[i][s] = true;
				} else if (num[i] <= s) {
					dp[i][s] = dp[i-1][s-num[i]];
				}
			}
		}

		return dp[n-1][S];
	}

	public static void main(String[] args) {
		int [] num = {1,3,4,8};

		System.out.println(solve(num, 6));

	}
}
