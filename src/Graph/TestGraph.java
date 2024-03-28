package Graph;

public class TestGraph {
	public static void main(String[] args) {
		
	String path = "h2.txt";
	Graph g = new UnGraph();
	g.loadData(path);
	//g.viewMatrix();
	//g.removeEdge(0,2);
	System.out.println();
	//g.viewMatrix();
	//g.DFS(6);
	//g.printList();
	g.BFS(0);
	System.out.println(g.havePath(2, 7));
	System.out.println(g.checkSingleGraph());
	g.printPath(2, 4);
	}

}
