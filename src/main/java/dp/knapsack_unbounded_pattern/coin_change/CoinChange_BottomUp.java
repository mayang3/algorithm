package dp.knapsack_unbounded_pattern.coin_change;

/**
 * @author neo82
 */
public class CoinChange_BottomUp {
	public static void main(String[] args) {
		int [] coins = {1,2,3};
		int total = 5;

		CoinChange_BottomUp bottomUp = new CoinChange_BottomUp();
		int count = bottomUp.coinChange(coins, total);

		System.out.println(count);
	}

	private int coinChange(int [] coins, int total) {
		int n = coins.length;

		int [][] dp = new int[n][total+1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		for (int i = 0; i < n; i++) {
			for (int t = 1; t <= total ; t++) {
				int sum1 = 0;
				int sum2 = 0;

				if (coins[i] <= t) {
					sum1 = dp[i][t-coins[i]];
				}

				if (i > 0) {
					sum2 = dp[i-1][t];
				}

				dp[i][t] = sum1 + sum2;
			}
		}

		return dp[n-1][total];
	}
}
