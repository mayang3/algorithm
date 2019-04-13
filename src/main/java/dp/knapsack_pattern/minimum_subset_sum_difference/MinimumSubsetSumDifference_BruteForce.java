package dp.knapsack_pattern.minimum_subset_sum_difference;

public class MinimumSubsetSumDifference_BruteForce {

	// 각 합의 최소값을 리턴한다.
	public int canPartition(int [] num) {
		return this.canPartitionRecursive(num, 0, 0, 0);
	}

	/**
	 * 단순하게 생각해보자.
	 *
	 * 현재 숫자를 S1 에 더하는 경우와 S2 에 더하는 두가지 경우가 존재한다.
	 *
	 * 시간복잡도는 O(2^n) 이 되고,
	 *
	 * 공간복잡도는 O(n) 이 된다.
	 *
	 * @param num
	 * @param currentIndex
	 * @param sum1
	 * @param sum2
	 * @return
	 */
	private int canPartitionRecursive(int [] num, int currentIndex, int sum1, int sum2) {
		// base check
		if (currentIndex == num.length) {
			return Math.abs(sum1 - sum2);
		}

		int diff1 = canPartitionRecursive(num, currentIndex+1, sum1+num[currentIndex], sum2);

		int diff2 = canPartitionRecursive(num, currentIndex+1,  sum1, sum2+num[currentIndex]);

		return Math.min(diff1, diff2);
	}

	public static void main(String[] args) {
		MinimumSubsetSumDifference_BruteForce mssf = new MinimumSubsetSumDifference_BruteForce();

		int[] num = {1, 2, 3, 9};

		System.out.println(mssf.canPartition(num));

		num = new int[]{1, 2, 7, 1, 5};

		System.out.println(mssf.canPartition(num));

		num = new int[]{1, 3, 100, 4};

		System.out.println(mssf.canPartition(num));
	}
}
