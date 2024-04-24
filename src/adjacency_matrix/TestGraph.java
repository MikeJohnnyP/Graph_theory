package adjacency_matrix;

public class TestGraph {
	public static void main(String[] args) {

		String path = "G2.txt";
		Graph g = new DirGraph();
		g.loadData(path);
		// g.viewMatrix();
		// g.removeEdge(0,2);
		System.out.println();
		// g.viewMatrix();
		// g.DFS(6);
		// g.printList();
		// g.BFS(0);
		// System.out.println(g.havePath(2, 7));
		// System.out.println(g.checkSingleGraph());
		// g.printPath(2, 4);
		// System.out.println(g.isConected());
		// System.out.println(g.isBipartiteGraph());
		Graph.printList(g.findEulerTour());
		// System.out.println(g.isEulerGraph());
		// System.out.println(g.isEulerHalfGraph());
	}

}
