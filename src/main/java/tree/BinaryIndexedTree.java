package tree;

/**
 * FenWick Tree
 *
 * 이게 좀더 직관적이다. 이걸 쓰자.
 *
 */
public class BinaryIndexedTree {
	static final int MAX = 1000;

	static int [] BITree = new int[MAX];

	/**
	 * n --> No. of elements present in input array.
	 *
	 * BITree[0..n] -->
	 * Array that represents Binary Indexed Tree.
	 *
	 * arr[0...n-1] -->
	 * Input Array for which prefix sum in evaluated.
	 *
	 */

	/**
	 * Returns sum of arr[0...index].
	 *
	 * This function assumes that the array is preprocessed and partial sums of array elements are stored in BITree[].
	 *
	 * @param index
	 * @return
	 */
	int getSum (int index) {
		// initialize result
		int sum = 0;

		// index in BITree[] is 1 more than the index in arr[]
		index++;

		// Traverse ancetors of BITree[index]
		while (index > 0) {
			// Add current element of BITree to sum
			sum += BITree[index];

			// Move index to parent node in getSum View
			index -= index & (-index);
		}

		return sum;
	}

	/**
	 * Updates a node in Binary Index Tree (BITree) at given index in BITree.
	 *
	 * The given value 'val' is added to BITree[i] and all of its ancestors in tree.
	 *
	 * @param n
	 * @param index
	 * @param val
	 */
	public static void updateBIT(int n, int index, int val) {
		// index in BITree [] is 1 more than the index in arr[]
		index = index + 1;

		// Traverse all ancestors and add 'val'
		while (index <= n) {
			// Add 'val' to current node of BIT tree
			BITree[index] += val;

			// Update index to that of parent in update view.
			index += index & (-index);
		}
	}

	/**
	 * Function to construct fenwick tree from given array.
	 *
	 * @param arr
	 * @param n
	 */
	void contructBITree(int arr [], int n) {
		// Initialize BITree[] as 0
		for (int i = 1; i < n; i++) {
			BITree[i] = 0;
		}

		// Store the actual values in BITree[]. using update()
		for (int i = 0; i < n; i++) {
			updateBIT(n, i, arr[i]);
		}
	}

	public static void main(String[] args) {
		int [] freq = {1,2,3,4,5};

		int n = freq.length;

		BinaryIndexedTree tree = new BinaryIndexedTree();

		// Build fenwick tree from given array.
		tree.contructBITree(freq, n);

//		System.out.println("Sum of elemtns in arr[0..5] is = " + tree.getSum(6));

		// Let use test the update operation.
//		freq[3] += 6;

		System.out.println(tree.getSum(4));
		// Update BIT for above change in arr[]
		updateBIT(n, 2, 6);

		System.out.println(tree.getSum(4));


		// Find sum after the value is updated.
		System.out.println("Sum of elemtns in arr[0..5] after update is = " + tree.getSum(5));
	}


}
