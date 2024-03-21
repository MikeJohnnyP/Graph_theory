package Graph;

public class TestGraph {
	public static void main(String[] args) {
		
	String path = "mt.txt";
	Graph g = new UnGraph();
	g.loadData(path);
	g.viewMatrix();
	g.removeEdge(0,2);
	System.out.println();
	g.viewMatrix();
	g.DFS(3);
	g.printList();
	}

}
