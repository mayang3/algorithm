package dp;

/**
 * Initialize:
 *     max_so_far = 0
 *     max_ending_here = 0
 *
 * Loop for each element of the array
 *   (a) max_ending_here = max_ending_here + a[i]
 *   (b) if(max_ending_here < 0)
 *             max_ending_here = 0
 *   (c) if(max_so_far < max_ending_here)
 *             max_so_far = max_ending_here
 * return max_so_far
 *
 * O(n)
 *
 */
public class Kadane {

	/**
	 *
	 * Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the array (maxEndingHere is used for this)
	 *
	 * maxEndingHere : 순회하면서 양의정수가 되는동안 계속 감시하는 variable
	 *
	 * And keep track of maximum sum contiguous segment among all positive segments. (maxSoFar is used for this.)
	 *
	 * maxSoFar : 위에서 감시한 모든 양의 정수 값들중에 가장 max 값을 trace
	 *
	 * Each time we get a positive sum compare it with maxSoFar and update maxSoFar if it is greater than maxSoFar
	 *
	 * Lets take the example:
	 *     {-2, -3, 4, -1, -2, 1, 5, -3}
	 *
	 *     max_so_far = max_ending_here = 0
	 *
	 *     for i=0,  a[0] =  -2
	 *     max_ending_here = max_ending_here + (-2)
	 *     Set max_ending_here = 0 because max_ending_here < 0
	 *
	 *     for i=1,  a[1] =  -3
	 *     max_ending_here = max_ending_here + (-3)
	 *     Set max_ending_here = 0 because max_ending_here < 0
	 *
	 *     for i=2,  a[2] =  4
	 *     max_ending_here = max_ending_here + (4)
	 *     max_ending_here = 4
	 *     max_so_far is updated to 4 because max_ending_here greater
	 *     than max_so_far which was 0 till now
	 *
	 *     for i=3,  a[3] =  -1
	 *     max_ending_here = max_ending_here + (-1)
	 *     max_ending_here = 3
	 *
	 *     for i=4,  a[4] =  -2
	 *     max_ending_here = max_ending_here + (-2)
	 *     max_ending_here = 1
	 *
	 *     for i=5,  a[5] =  1
	 *     max_ending_here = max_ending_here + (1)
	 *     max_ending_here = 2
	 *
	 *     for i=6,  a[6] =  5
	 *     max_ending_here = max_ending_here + (5)
	 *     max_ending_here = 7
	 *     max_so_far is updated to 7 because max_ending_here is
	 *     greater than max_so_far
	 *
	 *     for i=7,  a[7] =  -3
	 *     max_ending_here = max_ending_here + (-3)
	 *     max_ending_here = 4
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		int [] a = {-2, -1, -3, -4, -5, -4};

		// SubSequence ...
		System.out.println(maxSubArraySum(a));
	}

	/**
	 * 1. array 의 맨 앞 index 부터 차례대로 더해나가면서, 최대값을 갱신시켜준다.
	 *
	 * 2. 차례대로 더해나간 값이 음수가 될 경우에는 지금까지의 최대값과 비교한 후, temporary sum 값을 0으로 설정한다.
	 *	1-1. (핵심) 음수가 될 경우에는 음수끼리 더할수록 더욱 작아지므로, 계속 더할 필요가 없다.
	 *	1-2. (핵심) 그러므로, 음수가 될 경우에는 항상 temporary sum 값을 0으로 설정하는것이 유효하다.
	 *
	 * 3. 만약 모든 수가 양수라면 무조건 모든 수를 더하는 것이 유리하다.
	 * 4. 만약 모든 수가 음수라면 무조건 가장 작은 수를 출력해야 한다. -> 수를 더하면 더할수록 작아진다.
	 * 5. 만약 음수와 양수가 섞여 있다면, 현재까지 더한수가 가장 큰수를 찾아야 한다.
	 * 	5-1. 현재까지 더한 수가 음수인 경우에는, 이전까지 더했던 수를 더이상 신경쓸 필요가 없다.
	 * 	5-2. 왜냐하면, 다음 수가 양수인 경우, 현재 수가 음수이므로 -> 현재수를 더하지 않고 바로 다음수부터 sum 하는 것이 유리하다.
	 * 	5-3. 다음 수가 음수인 경우, 현재 수가 음수이므로 현재수까지 더하면 더욱 작아진다. -> 바로 다음수부터 sum 하는 것이 유리하다.
	 *
	 *
	 * @param a
	 * @return
	 */
	static int maxSubArraySum(int [] a) {
		int size = a.length;

		int maxSoFar = Integer.MIN_VALUE; // 처음부터 끝까지의 최대값
		int maxEndingHere = 0; // 여기서 끝나는 최대값

		for (int i = 0; i < size; i++) {
			maxEndingHere += a[i];

			// 가장 최근의 값이 여태까지의 최대값보다 크다면, maxSoFar 를 변경한다.
			if (maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
			}

			// 현재 위치까지의 합이 음수라면.. 0으로 설정한다.
			if (maxEndingHere < 0) {
				maxEndingHere = 0;
			}
		}

		return maxSoFar;
	}
}
