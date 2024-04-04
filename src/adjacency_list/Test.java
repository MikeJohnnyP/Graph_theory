package adjacency_list;

public class Test {
		public static void main(String[] args) {
			UndirectGraph g1 = new UndirectGraph();
			g1.addVertex("a");
			g1.addVertex("b");
			g1.addVertex("c");
			g1.addVertex("d");
			g1.addEdge("a", "b");
			g1.addEdge("b", "d");
			g1.addEdge("b", "c");
			
			g1.printAdjList();
			System.out.println("Kiểm tra đồ thị vô hướng có liên thông: "+g1.isConnect("b", "c"));
			System.out.println("Kiểm tra đồ thị vô hướng có liên thông: "+g1.isConnect("a", "d"));
			System.out.println("Số cạnh đồ thị vô hướng: "+g1.edges());
			System.out.println("Tổng các bậc đồ thị vô hướng: "+g1.allDegree());
			System.out.println("Bậc từng đỉnh đồ thị vô hướng: "+g1.degree("b"));
			
			DirectGraph g2 = new DirectGraph();
			g2.addVertex("a");
			g2.addVertex("b");
			g2.addVertex("c");
			g2.addVertex("d");

			

			g2.addEdge("a", "b");
			g2.addEdge("a", "d");
			g2.addEdge("c", "b");
			g2.addEdge("c", "a");
			g2.addEdge("b", "a");

			
			g2.printAdjList();
			System.out.println("Kiểm tra đồ thị có hướng liên thông: "+g2.isConnect("a","c"));
			System.out.println("Kiểm tra đồ thị có hướng liên thông: "+g2.isConnect("c","a"));
			System.out.println("Nửa bậc ra đỉnh của đồ thị có hướng là:  "+g2.outDegree("a"));
			System.out.println("Nửa bậc trong đỉnh của đồ thị có hướng là:  "+g2.inDegree("a"));
			System.out.println("Tổng nữa bậc ra đồ thị có hướng: "+g2.outAllDegree());
			System.out.println("Tổng nữa bậc trong đồ thị có hướng: "+g2.inAllDegree());
			System.out.println("Tổng số cạnh đồ thị có hướng: "+g2.edges());



			
		}
	
		
}
