package adjacency_list;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class Graph {

	protected Map<String, Set<String>> adjList;

	public Graph() {
		this.adjList = new LinkedHashMap<>();
	}

	public void printAdjList() {
		for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}
	}

	public abstract void addVertex(String v);

	public abstract void removeVertex(String v);

	public abstract void addEdge(String v, String u);

	public abstract void removeEdge(String v, String u);

	public abstract boolean isConnect(String v, String u);

	public abstract boolean isEulerGraph();

	public abstract boolean isHalfEulerGraph();

	public abstract List<String> eulerPath();

	public abstract List<String> eulerCycle();

	public abstract int edges();

	public void loadData(String filePath) {
		Path path = Paths.get(filePath);
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String k[] = line.split("\t");
				if (k.length > 2) {
					for (int i = 0; i < k.length; i++) {
						adjList.put(k[i], new HashSet<String>());
					}
				} else {
					if (adjList.containsKey(k[0])) {
						String[] edge = k[1].split(" ");
						for (int j = 0; j < edge.length; j++) {
							adjList.get(k[0]).add(edge[j]);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public void loadDataWithMatrix(String filePath) {
		Path path = Paths.get(filePath);
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String k[] = line.split(" ");
				if (k.length == 1) {
					continue;
				} else {
					String x = String.valueOf(count);
					if (!adjList.containsKey(x)) {
						adjList.put(String.valueOf(count), new HashSet<String>());
					}
					for (int i = 0; i < k.length; i++) {
						if (!(k[i].equals("0"))) {
							adjList.get(x).add(k[i]);
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public boolean isConnected() {
		Map<String, Boolean> isVisited = new LinkedHashMap<String, Boolean>();
		Set<String> key = adjList.keySet();
		for (String p : key) {
			isVisited.put(p, false);
		}
		Queue<String> queue = new LinkedList<String>();

		queue.add(key.iterator().next());
		isVisited.put(key.iterator().next(), true);

		while (!(queue.isEmpty())) {
			String temp = queue.poll();

			for (String o : adjList.get(temp)) {
				if (!isVisited.get(o)) {
					isVisited.put(o, true);
					queue.add(o);
				}
			}
		}
		for (String p : key) {
			if (!isVisited.get(p)) {
				return false;
			}
		}
		return true;
	}

	public void DFS(String a) {
		Map<String, Boolean> isVisited = new LinkedHashMap<String, Boolean>();
		Set<String> key = adjList.keySet();
		for (String p : key) {
			isVisited.put(p, false);
		}
		Stack<String> stack = new Stack<String>();

		stack.push(a);
		isVisited.put(a, true);
		isVisited.put(a, true);

		while (!(stack.isEmpty())) {
			String temp = stack.pop();

			for (String o : adjList.get(temp)) {
				if (!isVisited.get(o)) {
					isVisited.put(o, true);
					stack.push(o);
					System.out.print(o + " ");
				}
			}
			System.out.println();
		}
	}

	public void BFS(String a) {
		Map<String, Boolean> isVisited = new LinkedHashMap<String, Boolean>();
		Set<String> key = adjList.keySet();
		for (String p : key) {
			isVisited.put(p, false);
		}
		Queue<String> queue = new LinkedList<String>();

		queue.add(a);
		isVisited.put(a, true);

		while (!(queue.isEmpty())) {
			String temp = queue.poll();
			System.out.print(temp + " ");

			for (String o : adjList.get(temp)) {
				if (!isVisited.get(o)) {
					isVisited.put(o, true);
					queue.add(o);
				}
			}
		}
	}

	public boolean havePath(String a, String b) {
		Map<String, Boolean> isVisited = new LinkedHashMap<String, Boolean>();
		Set<String> key = adjList.keySet();
		for (String p : key) {
			isVisited.put(p, false);
		}
		Queue<String> queue = new LinkedList<String>();

		queue.add(a);
		isVisited.put(a, true);

		while (!(queue.isEmpty())) {
			String temp = queue.poll();

			for (String o : adjList.get(temp)) {
				if (!o.equals(b)) {
					if (!isVisited.get(o)) {
						isVisited.put(o, true);
						queue.add(o);
					}
				} else
					return true;

			}

		}
		return false;
	}

	public void printPath(String a, String b) {
		Map<String, Boolean> isVisited = new LinkedHashMap<String, Boolean>();
		Set<String> key = adjList.keySet();
		ArrayList<String> printPath = new ArrayList<String>();
		for (String p : key) {
			isVisited.put(p, false);
		}
		Queue<String> queue = new LinkedList<String>();

		queue.add(a);
		isVisited.put(a, true);
		printPath.add(a);

		while (!(queue.isEmpty())) {
			String temp = queue.poll();

			for (String o : adjList.get(temp)) {
				if (!isVisited.get(o)) {
					if (!o.equals(b)) {
						isVisited.put(o, true);
						queue.add(o);
						printPath.add(o);
					} else {
						printPath.add(o);
						printPath.forEach((e) -> {
							System.out.print(e + " ");
						});
						return;

					}

				}

			}
		}
	}

	public boolean isBipartite() {
		Map<String, Integer> color = new HashMap<>();

		for (String node : adjList.keySet()) {
			if (!color.containsKey(node)) {
				color.put(node, 1);

				LinkedList<String> q = new LinkedList<>();
				q.add(node);

				while (!q.isEmpty()) {
					String u = q.poll();
					for (String v : adjList.get(u)) {
						if (!color.containsKey(v)) {
							color.put(v, 1 - color.get(u));
							q.add(v);
						} else if (color.get(v).equals(color.get(u))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
