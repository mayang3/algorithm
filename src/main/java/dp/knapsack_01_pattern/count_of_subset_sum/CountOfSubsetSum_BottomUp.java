package dp.knapsack_01_pattern.count_of_subset_sum;

/**
 * @author neo82
 */
public class CountOfSubsetSum_BottomUp {
	public static void main(String[] args) {
		int [] arr = {1,1,2,3};
		int S = 4;

		CountOfSubsetSum_BottomUp bottomUp = new CountOfSubsetSum_BottomUp();
		System.out.println(bottomUp.countSubsets(arr, S));
	}

	public int countSubsets(int[] num, int sum) {
		int n = num.length;

		int [][] dp = new int[n][sum+1];

		for (int y = 0; y < n; y++) {
			dp[y][0] = 1;
		}

		for (int x = 1; x <= sum; x++) {
			if (x - num[0] == 0) {
				dp[0][x] = 1;
			}
		}

		for (int y = 1; y < n; y++) {
			for (int x = 1; x <= sum; x++) {
				dp[y][x] += dp[y-1][x];

				if (x-num[y] >=0) {
					dp[y][x] += dp[y - 1][x - num[y]];
				}
			}
		}

		return dp[n-1][sum];
	}
}
