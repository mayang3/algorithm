package dp.knapsack_01_pattern.count_of_subset_sum;

/**
 * @author neo82
 */
public class CountOfSubsetSum_SlidingWindow {
	public static void main(String[] args) {
		int [] arr = {1,1,2,3};
		int n = 4;

		CountOfSubsetSum_SlidingWindow slidingWindow = new CountOfSubsetSum_SlidingWindow();
		System.out.println(slidingWindow.countSubsets(arr, n));
	}

	public int countSubsets(int[] num, int sum) {
		int n = num.length;
		int [] dp = new int[sum+1];

		dp[0] = 1;

		for (int s = 1; s < n; s++) {
			if (s - num[0] == 0) {
				dp[s] = 1;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int s = sum; s > 0 ; s--) {
				if (s-num[i] >= 0) {
					dp[s] += dp[s-num[i]];
				}
			}
		}

		return dp[sum];
	}
}
