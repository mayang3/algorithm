package dp.knapsack_unbounded_pattern.unbounded_knapsack;

/**
 * @author neo82
 */
public class UnboundedKnapsack_bruteForce {

	public static void main(String[] args) {
		int [] profits = {15, 50, 60, 90};
		int [] weights = {1, 3, 4, 5};

		UnboundedKnapsack_bruteForce ukb = new UnboundedKnapsack_bruteForce();
		int maxProfit = ukb.solveKnapsack(profits, weights, 8);

		System.out.println(maxProfit);
	}

	// time complexity : O(2^(n+c))
	// space complexity : O(N+C)
	public int solveKnapsack(int [] profits, int [] weights, int capacity) {
		return this.knapsackRecursive(profits, weights, capacity, 0);
	}

	private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
		if (capacity <= 0 || profits.length == 0 || weights.length != profits.length || currentIndex >= profits.length) {
			return 0;
		}

		// recursive call after choosing the items at the curerntIndex, Note that we recursive call on all.
		// items as we did not increment currentIndex
		int profit1 = 0;

		if (weights[currentIndex] <= capacity) {
			profit1 = profits[currentIndex] + knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex);
		}

		// recursive call after excluding the element at the currentIndex
		int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}
}
