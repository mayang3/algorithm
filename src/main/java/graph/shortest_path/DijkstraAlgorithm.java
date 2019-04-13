package graph.shortest_path;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 한점 -> 다수의 점 (음수가중치 X)
 *
 * 제약조건)
 * 1. 방향 그래프를 기준으로 동작한다.
 * -> 만약 무방향 그래프의 최단 경로를 찾기 위해서는 각각의 양방향 간선을 두 개의 일방 통행 간선으로 쪼개서 방향 그래프로 만들어야 한다.
 *
 * 1. 그래프 내의 음의 가중치 합을 가지는 사이클이 없어야 한다.
 * 2. 음의 가중치를 갖는 간선이 없어야 한다.
 *
 * 구현방법)
 * 1. 우선순위 큐를 사용하지 않는 방법 : O(V^2)
 * -> 정점(V) 의 수가 많고 간선(E) 의 수가 상대적으로 많을 때
 * 2. 우선순위 큐를 사용하는 방법 : O(ElogE)
 * -> 정점(V) 의 수가 많고 간선(E) 의 수는 상대적으로 적을 때
 *
 * [구현 - 우선순위 큐를 사용하지 않는 방법]
 *
 * Time Complexity : O(V^2)
 * -> 정점을 선택하는데 O(V) 가 들고, 아직 방문하지 않은 정점들 중 가장 짧은 정점을 찾는데 O(V) 가 든다.
 *
 * 1. 시작점을 제외한 모든 점의 경로를 무한으로 한다.
 * 2. 선택한 정으로부터 인접한 정점으로부터의 경로를 구하고, 그 경로의 거리가 이어진 정점의 거리보다 작을 경우 경로를 갱신해준다.
 * 3. 모든 정점을 살펴보면서, 아직 방문하지 않은 정점들중 거리가 가장 짧은 정점을 찾아서 선택해 준다.
 * 	  이후, 모든 정점을 선택할때까지 2번부터 반복한다.
 *
 * [구현 - 우선순위 큐를 사용하는 방법]
 *
 * Time Complexity : O(ElogE)
 *
 * 1.
 *
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class DijkstraAlgorithm {
	private static int INF = 987654321;

	static class Pair {
		int v;
		int cost;

		Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static class Dijkstra_PQ {
		List<Pair>[] adj;

		Dijkstra_PQ(List<Pair> [] adj) {
			this.adj = adj;
		}

		int [] execute(int start) {
			// 1. INF 로 채운 distance 배열을 만든다.
			int [] distance = makeDistance(start);
			// 2. min heap 기반의 pq 를 만든다.
			PriorityQueue<Pair> pq = makePriorityQueue(start);

			// 3. 전체 정점(or 간선) 탐색
			while (pq.isEmpty() == false) {
				Pair herePair = pq.poll();

				int here = herePair.v;
				int cost = herePair.cost;

				// 현재 정점이 앞에서 이미 처리되서, 현재의 ocst 보다 적은 값을 가지고 있는 경우
				// 이미 pq 에 정점이 들어갔는데, 이후에 더 짧은 경로가 발견되는 경우가 있을 수 있다.
				// 즉, 이 조건을 탄다는 것은 현재 here 에 관련된 정보가 두개 입력된 상태라고 볼 수 있다.
				if (distance[here] < cost) {
					continue;
				}

				for (int i = 0; i < adj[here].size(); i++) {
					Pair therePair = adj[here].get(i);

					int there = therePair.v;
					int nextDistance = distance[here] + therePair.cost;

					// 이미 처리된 cost 보다 더 짧은 경우에만 갱신한다.
					if (nextDistance < distance[there]) {
						distance[there] = nextDistance;
						pq.add(new Pair(there, nextDistance));
					}
				}
			}

			return distance;
		}

		PriorityQueue<Pair> makePriorityQueue(int start) {
			PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
			pq.add(new Pair(start, 0));

			return pq;
		}

		int[] makeDistance(int start) {
			int V = adj.length;

			int [] distance = new int[V];

			Arrays.fill(distance, INF);

			distance[start] = 0;

			return distance;
		}

	}

}
