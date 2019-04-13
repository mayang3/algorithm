package graph.topologycalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 위상 정렬은 방향 그래프이므로 두 정점을 쌍방으로 연결하면 안된다.
 *
 * 위상 정렬은 결국 깊이 우선 탐색에서 맨 마지막에 도달한 노드부터 스택애 넣어주고,
 *
 * 제일 처음 노드 순서대로 출력해주는 정렬방법이다.
 *
 * 이때 각 노드간에 끊어진 그래프라면 두 끊어진 그래프 사이에는 순서가 없게 된다.
 *
 * 그 순서는 문제에 따라 정의해줄 수도 있을듯..
 *
 */
public class TopologicalSortDfs {
	static List<Integer>[] adj;
	static Stack<Integer> stack = new Stack<>();
	static boolean [] visited;

	public static void main(String[] args) {
		adj = new List[6];
		visited = new boolean[6];

		for (int i = 0; i < 6; i++) {
			adj[i] = new LinkedList<>();
		}

//		g.addEdge(5, 2);
//		g.addEdge(5, 0);
//		g.addEdge(4, 0);
//		g.addEdge(4, 1);
//		g.addEdge(2, 3);
//		g.addEdge(3, 1);

		connect(5,2);
		connect(5,0);
		connect(4,0);
		connect(4,1);
		connect(2,3);
		connect(3,1);

		for (int i = 0; i < adj.length; i++) {
			if (visited[i] == false) {
				dfs(i);
			}
		}

		while (stack.isEmpty() == false) {
			System.out.println(stack.pop());
		}
	}

	private static void dfs(int here) {
		visited[here] = true;

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(there);
			}
		}

		stack.add(here);
	}

	static void connect(int u, int v) {
		adj[u].add(v);
	}
}
