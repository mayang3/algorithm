package dp.knapsack_pattern.equal_subset_sum_partition;

@SuppressWarnings("Duplicates")
public class PartitionSet_BottomUp {

	boolean canPartition(int [] num) {

		int sum = 0;

		for (int i = 0; i < num.length; i++) {
			sum += num[i];
		}

		if (sum % 2 != 0) {
			return false;
		}

		int n = num.length;

		sum /= 2;

		boolean [][] dp = new boolean[n][sum+1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		// 숫자가 하나일때는 그 숫자가 sum 과 같은지만 확인하면 된다.
		// 같다면 S-num[i] = 0 이 되기 때문에..
		for (int s = 0; s <= sum; s++) {
			dp[0][s] = (num[0] == s);
		}

		for (int i=1; i<n ; i++) {
			for (int s = 1; s <= sum ; s++) {
				if (dp[i-1][s]) {
					dp[i][s] = true;
				} else if (s >= num[i]) {
					dp[i][s] = dp[i-1][s-num[i]];
				}
			}
		}

		return dp[n-1][sum];
	}



	public static void main(String[] args) {
		PartitionSet_BottomUp ps = new PartitionSet_BottomUp();
		int[] num = {1, 2, 3, 4};
		System.out.println(ps.canPartition(num));
		num = new int[]{1, 1, 3, 4, 7};
		System.out.println(ps.canPartition(num));
		num = new int[]{2, 3, 4, 6};
		System.out.println(ps.canPartition(num));
	}
}
