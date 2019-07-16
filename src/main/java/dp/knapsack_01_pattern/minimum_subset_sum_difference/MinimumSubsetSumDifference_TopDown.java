package dp.knapsack_01_pattern.minimum_subset_sum_difference;

public class MinimumSubsetSumDifference_TopDown {

	// 각 합의 최소값을 리턴한다.
	public int canPartition(int [] num) {
		int sum = 0;

		for (int i = 0; i < num.length; i++) {
			sum += num[i];
		}

		Integer [][] dp = new Integer[num.length][sum+1];

		return this.canPartitionRecursive(dp, num, 0, 0, 0);
	}

	/**
	 * topdown for dp
	 *
	 * @param num
	 * @param currentIndex
	 * @param sum1
	 * @param sum2
	 * @return
	 */
	private int canPartitionRecursive(Integer [][] dp, int [] num, int currentIndex, int sum1, int sum2) {
		// base check
		if (currentIndex == num.length) {
			return Math.abs(sum1 - sum2);
		}

		if (dp[currentIndex][sum1] != null) {
			return dp[currentIndex][sum1];
		}

		int diff1 = canPartitionRecursive(dp, num, currentIndex + 1, sum1 + num[currentIndex], sum2);

		int diff2 = canPartitionRecursive(dp, num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

		return dp[currentIndex][sum1] = Math.min(diff1, diff2);
	}

	public static void main(String[] args) {
		MinimumSubsetSumDifference_TopDown mssf = new MinimumSubsetSumDifference_TopDown();

		int[] num = {1, 2, 3, 9};

		System.out.println(mssf.canPartition(num));

		num = new int[]{1, 2, 7, 1, 5};

		System.out.println(mssf.canPartition(num));

		num = new int[]{1, 3, 100, 4};

		System.out.println(mssf.canPartition(num));
	}
}
