package dp.knapsack_pattern.subset_sum;

public class SubsetSum_TopDown {


	public boolean solve(int [] num, int S) {
		Boolean [][] cache = new Boolean[num.length][S+1];

		return solveRecursive(cache, num, S, 0);
	}


	public boolean solveRecursive(Boolean [][] cache, int [] num, int S, int current) {
		if (S == 0) {
			return true;
		}

		if (current < 0 || current >= num.length) {
			return false;
		}

		if (cache[current][S] != null) {
			return cache[current][S];
		}

		// 현재 인덱스를 선택하는 경우
		if (num[current] <= S) {
			if (solveRecursive(cache, num, S-num[current], current+1)) {
				return cache[current][S] = true;
			}
		}

		// 현재 인덱스를 선택하지 않는 경우
		return cache[current][S] = solveRecursive(cache, num, S, current+1);
	}

	public static void main(String[] args) {
		SubsetSum_TopDown topDown = new SubsetSum_TopDown();

		int [] num = {1,3,4,8};

		boolean solve = topDown.solve(num, 6);

		System.out.println(solve);
	}
}
