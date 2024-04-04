package adjacency_list;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Graph {
	
	protected Map<String, Set<String>>adjList;

	public Graph() {
		super();
		this.adjList = new LinkedHashMap<>();
	}
	public void printAdjList() {
		for(Map.Entry<String, Set<String>>entry: adjList.entrySet()) {
			System.out.println(entry.getKey()+"-->"+entry.getValue());
		}
	}
	public abstract void addVertex(String v);
	public abstract void removeVertex(String v);	   
	public abstract void addEdge(String v, String u);
	public abstract void removeEdge(String v, String u);
	public abstract boolean isConnect(String v, String u);
	public abstract int edges();
	
}