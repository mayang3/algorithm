package graph.shortest_path;

/**
 * 모든점 -> 모든점
 *
 * @author neo82
 */
public class FloydWarshallAlgorithm {

	static class Pair {
		int v;
		int cost;

		public Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static class Floyd {
		int V;
		int [][] graph;
		int [][][] C;

		public Floyd(int [][] graph, int V) {
			this.graph = graph;
			this.V = V;
			this.C = new int[V][V][V];
		}

		void allPairShortestPath1() {
			// C[0] 을 초기화
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (i != j) {
						// C_{k-1} 에서 k 가 0이니, C_-1 이 되는데, 이 것은 S_k={}, 공집합임을 의미한다.
						// C_0 은 S_k={0} 을 의미한다. 즉 정점 0 을 경유점으로 가지는 경우이다. (공집합 아님)
						C[0][i][j] = Math.min(graph[i][j], graph[i][0] + graph[0][j]);
					} else {
						// 자기 자신의 최단거리는 항상 0
						C[0][i][j] = 0;
					}
				}
			}

			// C[k-1] 이 있으면 C[k] 를 계산할 수 있다.
			for (int k = 1; k < V; k++) {
				for (int i = 0; i < V; i++) {
					for (int j = 0; j < V; j++) {
						// C[k-1][i][j] 는 k-1 번까지의 정점들을 경유점으로 했을때, 최단거리가 저장되어 있다.
						// 즉, k-1 번째까지의 정점들을 경유점으로 했을때의 최단거리와 현재 나의
						C[k][i][j] = Math.min(C[k-1][i][j], C[k-1][i][k] + C[k-1][k][j]);
					}
				}
			}
		}
	}
}
