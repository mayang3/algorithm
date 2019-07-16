package dp.knapsack_unbounded_pattern.unbounded_knapsack;

/**
 * @author neo82
 */
public class UnboundedKnapsack_BottomUp {
	public static void main(String[] args) {
		int [] profits = {15, 50, 60, 90};
		int [] weights = {1, 3, 4, 5};
		int capacity = 8;

		UnboundedKnapsack_BottomUp ubu = new UnboundedKnapsack_BottomUp();
		int maxProfit = ubu.knapsack(profits, weights, capacity);

		System.out.println(maxProfit);
	}

	private int knapsack(int [] profits, int [] weights, int capacity) {
		int n = profits.length;

		int [][] dp = new int[n][capacity + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}

		for (int i = 0; i < n; i++) {
			for (int c = 1; c <= capacity ; c++) {
				int profit1 = 0;
				int profit2 = 0;

				if (weights[i] <= c) {
					profit1 = profits[i] + dp[i][c-weights[i]];
				}

				if (i > 0) {
					profit2 = dp[i-1][c];
				}

				dp[i][c] = Math.max(profit1, profit2);
			}
		}

		return dp[n-1][capacity];
	}
}
