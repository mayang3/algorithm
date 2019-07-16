package dp.knapsack_01_pattern.subset_sum;

/**
 * @author baejunbeom
 */
public class SubsetSum_Sliding_Window {
	public static boolean solve(int [] num, int sum) {
		int n = num.length;

		boolean [] dp = new boolean[sum+1];

		dp[0] = true;

		for (int s = 1; s <= sum; s++) {
			if (num[0] == s) {
				dp[s] = true;
			}
		}

		for (int i = 1; i < n; i++) {
			// 여기서 꼭! sum 을 뒤에서부터 계산해야 한다.
			// 하나의 row 에 겹쳐쓰므로, 앞에서부터 쓴다면 이전 row 의 값이 현재값이 영향을 줄 수 있다.
			// 이해 안되면 테이블 그려볼것..
			for (int s = sum; s >= 0; s--) {
				if (dp[s]) {
					dp[s] = true;
				} else if (num[i] <= s) {
					dp[s] = dp[s-num[i]];
				}
			}
		}

		return dp[sum];
	}

	public static void main(String[] args) {
		int [] num = {1,3,4,8};

		System.out.println(solve(num, 6));
	}


}
