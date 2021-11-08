package graph.shortest_path;

public class FloydWarshallAlgorithm_Improve {

	static class Floyd {
		int V;
		// adj[u][v], u 에서 v 로 가는 간선의 가중치. 간선이 없으면 아주 큰 값을 넣는다.
		int [][] adj;

		public Floyd(int V, int[][] adj) {
			V = V;
			this.adj = adj;
		}

		void floyd() {
			for (int i = 0; i < V; i++) {
				adj[i][i] = 0;
			}

			for (int k = 0; k < V; k++) {
				for (int i = 0; i < V; i++) {
					for (int j = 0; j < V; j++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
		}
	}
}
