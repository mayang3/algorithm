package tree;

public class SegmentTree {

	// root node 를 1번으로, left child -> 2*i , right child -> 2*i+1 로 표현한다.
	int [] arr;
	int n; // length of input

	public SegmentTree(int[] input) {
		this.n = input.length;
		this.arr = new int[n * 4];
		init(input, 0, n-1, 1);
	}

	int init(int [] input, int left, int right, int node) {
		if (left == right) {
			return this.arr[node] = input[left];
		}

		int mid = (left + right) / 2;

		int leftMin = init(input, left, mid, node * 2);
		int rightMin = init(input, mid+1, right, node * 2 + 1);

		return this.arr[node] = Math.min(leftMin, rightMin);
	}

	int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {
			return Integer.MAX_VALUE;
		}

		if (left <= nodeLeft && nodeRight <= right) {
			return this.arr[node];
		}

		int mid = (nodeLeft + nodeRight) / 2;

		return Math.min(query(left, right, node * 2, nodeLeft, mid),
						query(left, right, node * 2 + 1, mid+1, nodeRight));
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n-1);
	}

	public static void main(String[] args) {
		int [] input = {4, 13, 10, 7, 6, 7, 13, 15, 1, 4};

		SegmentTree segmentTree = new SegmentTree(input);

		System.out.println(segmentTree.query(1, 2));

	}
}
