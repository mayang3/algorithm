package dp.knapsack_pattern.equal_subset_sum_partition;

public class PartitionSet_TopDown {
	static Boolean [][] dp;

	public boolean canPartition(int [] num) {
		int sum = 0;

		for (int i = 0; i < num.length; i++) {
			sum += num[i];
		}

		if (sum % 2 != 0) {
			return false;
		}

		dp = new Boolean[num.length][sum/2+1];

		return canPartitionRecursive(num, sum / 2, 0);
	}


	boolean canPartitionRecursive(int [] num, int sum, int current) {
		if (sum == 0) {
			return true;
		}

		int n = num.length;

		if (current < 0 || current >= n) {
			return false;
		}

		if (dp[current][sum] != null) {
			return dp[current][sum];
		}

		if (num[current] <= sum) {
			if (canPartitionRecursive(num, sum - num[current], current + 1)) {
				return dp[current][sum] = true;
			}
		}

		return dp[current][sum] = canPartitionRecursive(num, sum, current + 1);
	}

	public static void main(String[] args) {
		PartitionSet_TopDown ps = new PartitionSet_TopDown();
		int[] num = {1, 2, 3, 4};
		System.out.println(ps.canPartition(num));
		num = new int[]{1, 1, 3, 4, 7};
		System.out.println(ps.canPartition(num));
		num = new int[]{2, 3, 4, 6};
		System.out.println(ps.canPartition(num));
	}
}
