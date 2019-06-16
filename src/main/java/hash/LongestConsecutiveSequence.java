package hash;

import java.util.HashSet;

/**
 * https://www.geeksforgeeks.org/longest-consecutive-subsequence/
 *
 * Time Complexity : O(n)
 * Space Complexity : O(n)
 *
 * @author neo82
 */
public class LongestConsecutiveSequence {
	static int findLongestConseqSubseq(int arr[],int n) {
		HashSet<Integer> S = new HashSet<>();

		int ans = 0;

		for (int i=0; i<n; ++i) {
			S.add(arr[i]);
		}

		for (int i=0; i<n; ++i) {
			if (!S.contains(arr[i]-1)) {
				int j = arr[i];

				while (S.contains(j)) {
					j++;
				}

				ans = Math.max(ans, j-arr[i]);
			}
		}
		return ans;
	}

	// Testing program
	public static void main(String args[])
	{
		int arr[] =  {1, 9, 3, 10, 4, 20, 2};
		int n = arr.length;
		System.out.println("Length of the Longest consecutive subsequence is " +
			findLongestConseqSubseq(arr,n));
	}
}
