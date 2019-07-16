package dp.knapsack_01_pattern.count_of_subset_sum;

/**
 * @author neo82
 */
public class CountOfSubsetSum_TopDown {
	public static void main(String[] args) {
		int [] arr = {1,2,7,1,5};
		int S = 9;

		Integer [][] dp = new Integer[arr.length][S+1];

		System.out.println(solve(dp, arr, S, 0));
	}

	public static int solve(Integer[][] dp, int[] arr, int S, int currentIndex) {
		if (S == 0) {
			return 1;
		} else if (currentIndex >= arr.length) {
			return 0;
		}

		if (dp[currentIndex][S] != null) {
			return dp[currentIndex][S];
		}

		int count = 0;

		if (S - arr[currentIndex] >= 0) {
			count += solve(dp, arr, S-arr[currentIndex], currentIndex+1);
		}

		count += solve(dp, arr, S, currentIndex + 1);

		return dp[currentIndex][S] = count;
	}
}
