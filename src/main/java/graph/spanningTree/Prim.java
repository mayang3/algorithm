package graph.spanningTree;

/**
 * A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
 *
 * The Program is for adjacency matrix representation of the graph.
 *
 */
public class Prim {

	/**
	 *
	 */
	static class MST {
		// Number of vertices in the graph
		static final int V = 5;

		// A utility function to find the vertex with minimum key
		// from the set of vertices not yet included in MST
		int minKey(int [] key, Boolean [] mstSet) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int v = 0; v < V; v++) {
				if (mstSet[v] == false && key[v] < min) {
					min = key[v];
					minIndex = v;
				}
			}

			return minIndex;
		}

		// A utility function to print the constructed MST stored in parent[]
		void printMST(int [] parent, int n, int [][] graph) {
			System.out.println("Edge \tWeight");

			for (int i = 1; i < V; i++) {
				System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
			}
		}

		/**
		 * 프림 알고리즘을 추상화 해보면...
		 *
		 * 1. 변수 선언
		 * 	1-1. MST 결과를 저장하기 위한 parent 선언
		 * 	1-2. 최소 가중치를 나타낼 key 선언
		 * 	1-3. mstSet 선언
		 * 	1-4. key 의 가중치값 INFINITE 로 설정, mstSet false 로 설정
		 *
		 * 2. 정점 0을 MST 의 최초값으로 추가한다.
		 * 3. 추가할 최소 정점을 찾는다.
		 * 4. 다음 후보 선택을 위해 모든 정점들의 parent[] 와 key[] 값을 업데이트 한다.
		 * 	4-1. parent[] 에 추가되었다는 것은 MST 에 추가되었다는 뜻이다.
		 *
		 *
		 * @param graph
		 */
		// Function to construct and print MST for a graph represented using adjacency matrix representation
		void primMST(int [][] graph) {
			// Array to store constructed MST
			// prim 알고리즘의 수행결과로 생성된 MST 를 저장하기 위한 배열
			int [] parent = new int[V];

			// Key values used to pick minimum weight edge in cut
			// 최소 가중치를 나타낸다. 이 값을 저장하여 최소 가중치보다 작은 간선을 찾은 경우에만 업데이트 한다.
			int [] key = new int[V];

			// To represent set of vertices not yet included in MST
			// 정점들이 MST 에 포함되어 있는지 여부를 나타내는 boolean 집합
			Boolean [] mstSet = new Boolean[V];

			// Initialize all key as INFINITE
			// key 를 무한대의 값으로 초기화 한다.
			// 또한, 정점들을 모두 아직 포함 안됨으로 초기화 한다.
			for (int i = 0; i < V; i++) {
				key[i] = Integer.MAX_VALUE;
				mstSet[i] = false;
			}

			// Always include first 1st vertex in MST.
			// Make key 0 so that this vertex is picked as first vertex
			// 첫번째 정점은 항상 MST 에 포함된다.
			key[0] = 0;

			// First node is always root of MST.
			// 첫번째 정점은 항상 MST 의 root 가 된다.
			parent[0] = -1;

			// The MST will have V vertices.
			// V-1 만큼 순회한다.
			for (int count = 0; count < V - 1; count++) {
				// Pick thd minimum key vertex from the set of vertices not yet included in MST
				// 1. 아직 MST 에 포함되어 있지 않은 정점중 최소값을 추출해 낸다.
				int u = minKey(key, mstSet);

				// Add the picked vertex to the MST Set
				// 2. 해당 정점이 MST 에 추가되었음을 나타낸다.
				mstSet[u] = true;

				// Update key value and parent index of the adjacent vertices of the picked vertex.
				// Consider only those vertices which are not yet included in MST.

				// 다음 후보 간선을 선택하는 부분

				// 모든 정점들을 순회하면서
				// 선택된 정점의 인접한 정점들의 parent index 와 key value 를 업데이트 한다.
				// 아직 MST 에 포함되지 않은 정점들만을 고려한다.
				for (int v = 0; v < V; v++) {

					// graph[u][v] is non zero only for adjacent vertices of m
					// mstSet[v] is false for vertices not yet included in MST
					// Update the key only if graph[u][v] is smaller than key[v]

					// graph[u][v] 는 m 의 인접한 정점들에 대해 zero 가 아니여야 한다.
					// -> 인접행렬 이므로 graph[u][v] 가 0 이라는 말은 (u,v) 가 연결되어 있지 않다는 것을 의미한다.

					// mstSet[v] 값은 false 여야 한다. MST 에 아직 포함되지 않은 정점들에 대한 것이므로..

					// graph[u][v] 가 key[v] 보다 오직 적은 경우에만 key 를 업데이트 한다.
					// -> key[v] 는 최소 가중치를 의미한다.
					// 즉, graph[u][v] 가 현재까지 찾은 간선보다 가중치가 최소인 경우에만 MST 에 포함한다는 뜻이다.
					if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
						parent[v] = u;
						key[v] = graph[u][v];
					}
				}
			}

			printMST(parent, V, graph);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Let us create the following graph
            2    3
        (0)--(1)--(2)
        |   /  \   |
       6| 8/    \5 |7
        | /      \ |
        (3)-------(4)
              9         */

		MST t = new MST();

		int graph[][] = new int[][] {
				{0, 2, 0, 6, 0},
				{2, 0, 3, 8, 5},
				{0, 3, 0, 0, 7},
				{6, 8, 0, 0, 9},
				{0, 5, 7, 9, 0}
			};

		// Print the solution
		t.primMST(graph);
	}


}
