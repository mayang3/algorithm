package graph.topologycalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * http://jason9319.tistory.com/93
 *
 * idegree 란 한 정점에서 자신에게 들어오는 방향인 간선이다.
 * (outdegree 란 반대로 한 정점에서 다른 정점으로 나가는 방향인 간선이다.)
 *
 * 즉, 화살표 방향이 자기를 향하고 있는 간선의 개수이다.
 *
 * 1. 모든 정점의 idegree 개수를 세준 후, queue 에 indegree 가 0인 정점을 삽입해 준다.
 *
 * 2. 정점의 횟수만큼 loop 돌며 다음 작업을 해준다.
 * 2-1.
 * queue 의 front 를 추출하여, 해당 정점에서 나가는 간선을 다 지워준 후,
 * 지워진 간선에 의하여 indegree 가 0이 되는 정점들을 queue 에 삽입해준다.
 *
 * (이 때, 간선을 지워준다는 것은 간선의 종점인 정점들의 indegree 의 개수를 -1 해줌으로써 구현 가능하다.)
 *
 * 2-2. 이때, 큐에 동시에 여러개의 원소가 들어간다면 그 정점들의 순서가 바뀌어도 위상 정렬의 결과에는 영향을 주지 않는다.
 *
 * 2-3. 하지만, 우리가 정점의 횟수만큼 loop 를 돌리던 도중, queue 의 크기가 먼저 0 이 되어버린다면 cycle 이 존재하게 되므로,
 * 위상정렬이 불가능하다는 결론이 나온다.
 *
 * 사이클에 속하는 정점들이 존재한다면 그 정점들은 indegree 가 모두 1 이상이라 큐에 들어가지 않기 때문이다.
 *
 * 이러한 방식으로 구현되었을 때는 큐에서 추출되는 순서대로 위상정렬이 이루어진다.
 *
 * 이 때, 큐대신 min heap 을 사용한다면 indegree 가 0 인 정점이 동시에 여러개 있을 경우, 정점의 번호가 적은 순서대로 추출된다.
 *
 */
public class TopologicalSortIndegree {
	static List<Integer>[] adj;
	static int [] indegree;

	public static void main(String[] args) {
		adj = new List[6];
		indegree = new int[6];

		for (int i = 0; i < 6; i++) {
			adj[i] = new LinkedList<>();
		}

		connect(5,2);
		connect(5,0);
		connect(4,0);
		connect(4,1);
		connect(2,3);
		connect(3,1);

		topologicalSort();

	}

	static void topologicalSort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 1. indegree 가 0인 정점들을 넣어준다.
		for (int i = 0; i < 6; i++) {
			if (indegree[i] == 0) {
				pq.add(i * -1);
			}
		}

		// 2. 0 인 정점부터 indegree 를 지워 나가며 there 노드가 indegree 가 0인 된 경우 다음 타겟으로 넣어준다.
		while (pq.isEmpty() == false) {
			int here = pq.poll() * -1;

			System.out.println(here);

			for (int i = 0; i < adj[here].size() ; i++) {
				int there = adj[here].get(i);

				indegree[there]--;

				if (indegree[there] == 0) {
					pq.add(-1 * there);
				}
			}
		}

	}

	static void connect(int u, int v) {
		adj[u].add(v);
		indegree[v]++;
	}
}
