package dp.knapsack_unbounded_pattern.unbounded_knapsack;

/**
 * @author neo82
 */
public class UnboundedKnapsack_Topdown {
	public static void main(String[] args) {
		int [] profits = {15, 50, 60, 90};
		int [] weights = {1, 3, 4, 5};
		int capacity = 8;
		Integer [][] dp = new Integer[4][capacity+1];

		UnboundedKnapsack_Topdown unboundedKnapsack_topdown = new UnboundedKnapsack_Topdown();
		int maxProfit = unboundedKnapsack_topdown.knapsack(dp, profits, weights, capacity, 0);

		System.out.println(maxProfit);
	}

	private int knapsack(Integer [][] dp, int [] profits, int [] weights, int capacity, int currentIndex) {
		if (capacity == 0 || currentIndex >= profits.length) {
			return 0;
		}

		int profit1 = 0;

		if (capacity - weights[currentIndex] >= 0) {
			profit1 = profits[currentIndex] + knapsack(dp, profits, weights, capacity - weights[currentIndex], currentIndex);
		}

		int profit2 = knapsack(dp, profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}

}
