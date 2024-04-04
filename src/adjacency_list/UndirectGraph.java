package adjacency_list;

import java.util.HashSet;
import java.util.Set;

public class UndirectGraph extends Graph {

	@Override
	public void addVertex(String v) {
		adjList.put(v, new HashSet<String>());

	}

	@Override
	public void removeVertex(String v) {
		adjList.remove(v);

	}

	@Override
	public void addEdge(String v, String u) {
		if (!adjList.containsKey(v) || !adjList.containsKey(u)) {
				adjList.put(v, new HashSet<>());
		} else {
			adjList.get(v).add(u);
			if (!v.equals(u)) {
				adjList.get(u).add(v);
			}
		}

	}

	@Override
	public void removeEdge(String v, String u) {
		if (adjList.containsKey(v)) {
			adjList.get(v).remove(u);
			adjList.get(u).remove(v);

		}
	}

	
	public int degree(String vertex) {
		Set<String> adjacentVertices = adjList.get(vertex);
		if (adjacentVertices == null) {
			throw new IllegalArgumentException("Vertex does not exist in the graph.");
		}
		return adjacentVertices.size();
	}

	public boolean isConnect(String v, String u ) {
		if (adjList.containsKey(v)) {
			return adjList.get(v).contains(u);
		} else {
			return false;
		}
	}

	@Override
	public int edges() {
		return allDegree()/2;
	}

	
	public int allDegree() {
		int sum = 0;
		for (Set<String> numDegree : adjList.values()) {
			sum += numDegree.size();
		}
		return sum;
	}

}
