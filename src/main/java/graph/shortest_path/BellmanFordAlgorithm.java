package graph.shortest_path;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 한점 -> 다수의 점 (음수사이클 X)
 *
 * 다익스트라 알고리즘보다 느리지만, 음수가 있을때도 사용가능하다.
 *
 * 또한 사이클 여부를 확인할 수 있다.
 *
 * Time Complexity : O(VE)
 *
 * @author baejunbeom
 */
public class BellmanFordAlgorithm {

	static class Pair {
		int v;
		int cost;

		public Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static class BellmanFord {
		Map<Integer, List<Pair>> graph;
		int [] upper;
		int V;

		public BellmanFord(Map<Integer, List<Pair>> graph, int V) {
			this.graph = graph;
			this.upper = new int[V];
			this.V = V;

			// 시작점을 제외한
			Arrays.fill(upper, Integer.MAX_VALUE);
		}

		int [] bellmanFord(int src) {
			// 시작점의 최단거리는 0 이다.
			upper[src] = 0;

			boolean updated = false;

			// V 번 순회한다.
			for (int iter = 0; iter < this.V; iter++) { // O(V)
				updated = false;
				for (int here = 0; here < V; here++) { // O(E)
					for (int i = 0; i < graph.get(here).size(); i++) {
						int there = graph.get(here).get(i).v;
						int cost = graph.get(here).get(i).cost; // w(u,v)

						// (here, there) 간선을 따라 완화를 시도한다.
						if (upper[there] > upper[here] + cost) {
							// 성공
							upper[there] = upper[here] + cost;
							updated = true;
						}
					}
				}

				// 모든 간선에 대해 완화가 실패했을 경우 V-1 번 돌 필요도 없이 곧장 종료한다.
				// 만약, 분절된 그래프라면 이 로직은 돌아가지 않을듯..
				if (!updated) {
					break;
				}
			}

			// V번째 순회에서도 완화가 성공했다면 음수 사이클이 있다.
			if (updated) {
				return new int[0];
			}

			return upper;
		}
	}
}
