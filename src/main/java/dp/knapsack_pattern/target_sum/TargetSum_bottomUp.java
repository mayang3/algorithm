package dp.knapsack_pattern.target_sum;

import dp.knapsack_pattern.count_of_subset_sum.CountOfSubsetSum_SlidingWindow;

/**
 * @author neo82
 */
public class TargetSum_bottomUp {
	public static void main(String[] args) {
		int [] nums = {1,2,7,1};
		int S = 9;

		TargetSum_bottomUp targetSum_bottomUp = new TargetSum_bottomUp();
		System.out.println(targetSum_bottomUp.findTargetSubsets(nums, S));
	}

	public int findTargetSubsets(int[] num, int S) {
		int totalSum = 0;

		for (int v : num) {
			totalSum += v;
		}

		// 1. totalSum 이 S 보다 적으면 전부다 더해도 S 가 안된다는 뜻이므로 S 와 일치하는 집합을 찾을 수 없다.
		// 2. (S + totalSum) 이 홀수라면 (S+totalSum)/2 와 일치하는 집합을 찾을 수 없다.
		if (totalSum < S || (S + totalSum) % 2 == 1) {
			return 0;
		}

		return new CountOfSubsetSum_SlidingWindow().countSubsets(num, (S + totalSum) / 2);
	}
}
