package dp.knapsack_unbounded_pattern.minimum_coin_change;

public class MinimumCoinChange_TopDown {

	public int countChange(int[] denominations, int total) {
		Integer [][] dp = new Integer[denominations.length][total+1];

		int result = solve(dp, denominations, total, 0);

		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private int solve(Integer[][] dp, int[] denominations, int total, int i) {
		if (total == 0) {
			return 0;
		}

		if (denominations.length == 0 || i >= denominations.length) {
			return Integer.MAX_VALUE;
		}

		if (dp[i][total] != null) {
			return dp[i][total];
		}

		int count1 = Integer.MAX_VALUE;

		if (denominations[i] <= total) {
			int res = solve(dp, denominations, total - denominations[i], i);
			if (res != Integer.MAX_VALUE) {
				count1 = res + 1;
			}
		}

		int count2 = solve(dp,denominations, total, i+1);

		return dp[i][total] = Math.min(count1, count2);
	}

	public static void main(String[] args) {
		MinimumCoinChange_TopDown cc = new MinimumCoinChange_TopDown();
		int[] denominations = {1, 2, 3};
		System.out.println(cc.countChange(denominations, 5));
		System.out.println(cc.countChange(denominations, 11));
		System.out.println(cc.countChange(denominations, 7));
		denominations = new int[]{3, 5};
		System.out.println(cc.countChange(denominations, 7));

	}
}
