package dp.knapsack_01_pattern.minimum_subset_sum_difference;

/**
 * @author neo82
 */
@SuppressWarnings("ALL")
public class MinimumSubsetSumDifference_BottomUp {

	public int canPartition(int[] num) {
		int sum = 0;
		int n = num.length;

		for (int v : num) {
			sum += v;
		}

		boolean [][] dp = new boolean[num.length][sum / 2 + 1];

		for (int i = 0; i < num.length; i++) {
			dp[i][0] = true;
		}

		for (int s = 1; s < sum / 2 + 1; s++) {
			if (s == num[0]) {
				dp[0][s] = true;
			}
		}

		for (int i = 1; i < num.length; i++) {
			for (int s = 1; s < sum / 2 + 1; s++) {
				if (dp[i-1][s]) {
					dp[i][s] = true;
				} else if (s >= num[i]) {
					// 현재 num 을 더할 수 있는 경우
					// s-num[i] 가 true 일 경우, num[i] 를 더했을 경우에도 true 이다.
					dp[i][s] = dp[i-1][s-num[i]];
				}
			}
		}

		int sum1 = 0;

		// find the largest index in the last row which is true
		for (int s = sum / 2; s >= 0; s--) {
			if (dp[n-1][s]) {
				sum1 = s;
				break;
			}
		}

		int sum2 = sum - sum1;

		return Math.abs(sum2 - sum1);
	}


	public static void main(String[] args) {
		MinimumSubsetSumDifference_BottomUp ps = new MinimumSubsetSumDifference_BottomUp();
		int[] num = {1, 2, 3, 9};
		System.out.println(ps.canPartition(num));
		num = new int[]{1, 2, 7, 1, 5};
		System.out.println(ps.canPartition(num));
		num = new int[]{1, 3, 100, 4};
		System.out.println(ps.canPartition(num));
	}
}
