package dp.knapsack_unbounded_pattern.minimum_coin_change;

public class MinimumCoinChange_BottomUp {
	public int countChange(int[] denominations, int total) {
		int n = denominations.length;
		int [][] dp = new int[n][total+1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < total; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

		// populate the total=0 columns, as we don't need any coin to make zero total.
		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}

		for (int i = 0; i < n; i++) {
			for (int t = 1; t <= total; t++) {
				if (i > 0) {
					dp[i][t] = dp[i-1][t];
				}
				if (t >= denominations[i] && dp[i][t-denominations[i]] != Integer.MAX_VALUE) {
					dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]] + 1);
				}
			}
		}

		return dp[n-1][total] == Integer.MAX_VALUE ? -1 : dp[n-1][total];
	}

	public static void main(String[] args) {
		MinimumCoinChange_BottomUp cc = new MinimumCoinChange_BottomUp();
		int[] denominations = {1, 2, 3};
		System.out.println(cc.countChange(denominations, 5));
		System.out.println(cc.countChange(denominations, 11));
		System.out.println(cc.countChange(denominations, 7));
		denominations = new int[]{3, 5};
		System.out.println(cc.countChange(denominations, 7));
	}
}
