package dp.knapsack_unbounded_pattern.coin_change;

/**
 * @author neo82
 */
@SuppressWarnings("ALL")
public class CoinChange_TopDown {
	public static void main(String[] args) {
		int [] coins = {1,2,3};
		int total = 5;

		Integer [][] dp = new Integer[coins.length][total+1];

		CoinChange_TopDown ccb = new CoinChange_TopDown();
		int count = ccb.coinChange(dp, coins, total, 0);

		System.out.println(count);
	}

	private int coinChange(Integer [][] dp, int [] coins, int total, int currentIndex) {
		if (total == 0) {
			return 1;
		} else if (currentIndex >= coins.length) {
			return 0;
		}

		if (dp[currentIndex][total] != null) {
			return dp[currentIndex][total];
		}

		int sum1 = 0;

		if (coins[currentIndex] <= total) {
			sum1 = coinChange(dp, coins, total-coins[currentIndex], currentIndex);
		}

		int sum2 = coinChange(dp, coins, total, currentIndex+1);

		return dp[currentIndex][total] = (sum1 + sum2);
	}
}
