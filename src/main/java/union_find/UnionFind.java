package union_find;

/**
 * 상호 배타적 집합을 표현할 때 사용하는 독특한 트리이다.
 */
public class UnionFind {

	static class DisjointUnionSets {
		int [] rank;
		int [] parent;
		int n;

		public DisjointUnionSets(int n) {
			this.rank = new int[n];
			this.parent = new int[n];
			this.n = n;
			makeSet();
		}

		// Creates n Sets with single item in each.
		void makeSet() {
			for (int i = 0; i < n; i++) {
				// initially, all elements are in there own set.
				parent[i] = i;
			}
		}

		// Returns representative of x's set.
		// x 집합의 대표를 리턴한다.
		// 만약 parent[x] 가 x 와 같지 않다면. 계속 찾아 올라간다.
		int find(int x) {
			// Finds the representative of the set
			// that x is an element of
			if (parent[x] != x) {


				// find 의 결과를 parent[x] 에 저장함으로써, small cache 가 일어난다.
				// -> tree 의 height 를 compression 시킨다.

				// if x is not the parent of itself
				// Then x is not the representative of his set.
				parent[x] = find(parent[x]);

				// so we recursively call Find on its parent
				// and move i's node directly under the representative of this set
			}

			return parent[x];
		}

		// Unites the set that includes x and the set that includes x.
		void union(int x, int y) {
			// Find representatives of two sets.
			int xRoot = find(x);
			int yRoot = find(y);

			// Elements are in the same set, no need to unite anything.
			if (xRoot == yRoot) {
				return;
			}

			// rank 가 더 큰쪽이 root 가 된다.
			// 기본 개념은 항상 랭킹이 작은 트리를 랭킹이 큰 트리에 편입시키는 것이다.
			// If x's rank is less than y's rank.
			if (rank[xRoot] < rank[yRoot]) {

				// Then move x under y so that depth of tree remains less.
				parent[xRoot] = yRoot;
			} else if (rank[yRoot] < rank[xRoot]) { // else if y's rank is less than x's rank.

				// Then move y under x so that depth of tree remains less.
				parent[yRoot] = xRoot;
			} else { // if ranks are the same.

				// Then move y under x (doesn't matter which one goes where)
				parent[yRoot] = xRoot;

				// And increment the the result tree's rank by 1.
				rank[xRoot] = rank[xRoot] + 1;
			}
		}
	}

	public static void main(String[] args) {
		// Let there be 5 persons with ids as 0, 1, 2, 3, and 4
		int n = 5;

		DisjointUnionSets dus = new DisjointUnionSets(n);

		// 0 is a friend of 2
		dus.union(0, 2);

		// 4 is a friend of 2
		dus.union(4, 2);

		// 3 is a friend of 1
		dus.union(3, 1);

		// Check if 4 is a friend of 0
		if (dus.find(4) == dus.find(0)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

		// Check if 1 is a friend of 0
		if (dus.find(1) == dus.find(0)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
