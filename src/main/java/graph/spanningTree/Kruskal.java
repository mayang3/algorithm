package graph.spanningTree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Time Complexity : O(ElogE) or O(ElogB)
 */
@SuppressWarnings("Duplicates")
public class Kruskal {
	static class Graph {
		int V, E;
		Edge edge[];

		Graph(int v, int e, Edge [] edge) {

			this.V = v;
			this.E = e;
			this.edge = edge;
		}

		int kruskalMST() {
			// MST 의 결과를 저장할 Edge 배열
			Edge [] result  = new Edge[V-1];

			// step 1 : edge 를 가중치 순서대로 정렬한다.
			Arrays.sort(edge);

			DisjoinSet disjoinSet = new DisjoinSet(V);

			int e = 0;
			int i = 0;

			// 모든 edge 를 순회하면서, cycle 이 없는 경우에만 result 에 포함시킨다.
			// 스패닝 트리의 정의 자체가 최소한 정점들을 한번씩은 연결한 최소값의 트리이므로 최소 스패닝 트리에서 찾는 edge 는 무조건 V-1 개 이다.
			while (e < V - 1) {
				// step 2 : 가장 작은 edge 를 가져온다.
				Edge next = edge[i++];

				int x = disjoinSet.find(next.from);
				int y = disjoinSet.find(next.to);

				// step 3 : cycle 이 없다면 (같은 집합에 속해있지 않다면) result 에 포함시킨다.
				if (x != y) {
					result[e++] = next;
					disjoinSet.union(x, y);
				}
			}

			// step 4 : 가중치들의 합을 리턴한다.
			int sum = 0;

			for (i = 0; i < V-1; i++) {
				sum += result[i].weight;
			}

			return sum;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static class DisjoinSet {
		int [] parent;
		int [] rank;

		DisjoinSet(int n) {
			parent = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}

			return parent[x];
		}

		void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);

			if (xRoot == yRoot) {
				return;
			}

			if (rank[xRoot] < rank[yRoot]) {
				parent[xRoot] = yRoot;
				// 여기서 따로 rank 를 올려줄 필요는 없다.
			} else if (rank[yRoot] < rank[xRoot]) {
				parent[yRoot] = xRoot;
			} else {
				parent[yRoot] = xRoot;
				rank[xRoot]++;
			}
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int v = scanner.nextInt();
		int e = scanner.nextInt();

		Edge [] edges = new Edge[e];

		for (int i = 0; i < e; i++) {
			int from = scanner.nextInt() - 1;
			int to = scanner.nextInt() - 1;
			int weight = scanner.nextInt();

			edges[i] = new Edge(from, to, weight);
		}

		Graph graph = new Graph(v, e, edges);

		System.out.println(graph.kruskalMST());
	}
}
