package graph.shortest_path;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra2Algorithm {
	Map<Integer, List<Pair>> graph = new HashMap<>();
	int INF = Integer.MAX_VALUE;
	int V = 8;

	static class Pair {
		int v;
		int cost;

		public Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	int [] solve(int start) {
		int [] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);

		boolean [] visited = new boolean[V];

		dist[start] = 0;
		visited[start] = false;

		while (true) {
			// 아직 방문하지 않은 정점 중 가장 가까운 정점을 찾는다.
			int closest = INF, here = 0;

			for (int i = 0; i < V; i++) {
				if (dist[i] < closest && !visited[i]) {
					here = i;
					closest = dist[i];
				}
			}

			if (closest == INF) break;

			// 가장 가까운 정점의 거리를 갱신한다.
			visited[here] = true;

			for (int i = 0; i < graph.get(here).size(); i++) {
				Pair there = graph.get(here).get(i);

				if (visited[there.v]) continue;
				int nextDist = dist[here] + there.cost;

				dist[there.v] = Math.min(dist[there.v], nextDist);
			}
		}

		return dist;
	}
}
