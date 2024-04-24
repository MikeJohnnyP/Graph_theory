package adjacency_matrix;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Graph {

	int[][] arr;
	boolean[] visit;
	ArrayList<Integer> listView = new ArrayList<Integer>();

	public Graph(int[][] arr) {
		this.arr = arr;

	}

	public Graph() {

	}

	public int getLength() {
		return this.arr.length;
	}

	public int topNum() {
		return arr.length;
	}

	public boolean haveEdge(int x, int y) {
		if (arr[x][y] > 0)
			return true;
		return false;
	}

	public abstract Graph copyGraph();

	public abstract boolean addEdge(int x, int y);

	public abstract boolean removeEdge(int x, int y);

	public abstract boolean isEulerGraph();

	public abstract boolean isEulerHalfGraph();

	public abstract boolean isConected();

	public abstract List<Integer> findEulerTour();

	// DFS with recursion
	// public void DFS(int i) {
	// visit[i] = true;
	// listView.add(i);
	// for (int j = 0; j < this.arr.length; j++) {
	// if (this.arr[i][j] > 0 && this.visit[j] != true) {
	// DFS(j);
	// }
	// }
	// }

	// public void printList() {
	// for (int i : this.listView) {
	// System.out.print(i + " --> ");

	// }
	// }
	public static void printList(List<?> list) {
		if (list.isEmpty()) {
			System.out.println("Empty list");
			return;
		}
		for (Object o : list) {
			System.out.println(o);
		}
		System.out.println("--------------------------");
	}

	public void loadData(String filepath) {
		Path path = Paths.get(filepath);
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String k[] = line.split(" ");
				if (k.length == 1) {
					this.arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
					this.visit = new boolean[Integer.parseInt(k[0])];

				} else {
					for (int i = 0; i < k.length; i++) {
						this.arr[count][i] = Integer.parseInt(k[i]);
					}
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void viewMatrix() {
		for (int i = 0; i < this.arr.length; i++) {
			for (int j = 0; j < this.arr[i].length; j++) {
				System.out.print(this.arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void BFS(int x) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isVisited[] = new boolean[arr.length];
		queue.add(x);
		isVisited[x] = true;

		while (!(queue.isEmpty())) {
			int v = queue.poll();
			for (int i = 0; i < arr.length; i++) {
				if (arr[v][i] > 0) {
					if (isVisited[i] != true) {
						queue.add(i);
						isVisited[i] = true;
						System.out.print(i + " ");
					}
				}
			}
			System.out.println();
		}
	}

	public boolean havePath(int x, int y) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isVisited[] = new boolean[arr.length];
		queue.add(x);
		isVisited[x] = true;

		while (!(queue.isEmpty())) {
			int v = queue.poll();
			for (int i = 0; i < arr.length; i++) {
				if (arr[v][i] > 0) {
					if (isVisited[i] != true) {
						if (i == y)
							return true;
						else {
							queue.add(i);
							isVisited[i] = true;
						}

					}
				}
			}

		}
		return false;

	}

	public void printPath(int x, int y) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isVisited[] = new boolean[arr.length];
		ArrayList<Integer> path = new ArrayList<Integer>();
		queue.add(x);
		isVisited[x] = true;

		while (!(queue.isEmpty())) {
			int v = queue.poll();
			path.add(v);
			for (int i = 0; i < arr.length; i++) {
				if (arr[v][i] > 0) {
					if (isVisited[i] != true) {
						if (i == y) {
							path.add(i);
							for (int h : path) {
								System.out.print(h + " ");
							}
							return;
						} else {
							queue.add(i);
							isVisited[i] = true;
						}

					}
				}
			}

		}
	}

	public boolean checkSingleGraph() {
		for (int i = 0; i < arr.length; i++) {
			for (int y = 0; y < arr.length; y++) {
				if (i == y && arr[i][y] != 0)
					return false;
				if (arr[i][y] > 1)
					return false;
			}

		}
		return true;
	}

	public boolean DFS(int v) {
		boolean[] visited = new boolean[arr.length];
		LinkedList<Integer> stack = new LinkedList<Integer>();
		stack.add(v);
		visited[v] = true;
		while (stack.size() != 0) {
			v = stack.poll();
			for (int i = 0; i < arr.length; i++) {
				if (arr[v][i] > 0 && visited[i] == false) {
					stack.add(i);
					visited[i] = true;
				}
			}
		}
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isBipartiteGraph() {
		int[] colorArr = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			colorArr[i] = -1;
		}

		for (int i = 0; i < arr.length; i++) {
			if (colorArr[i] == -1) {
				if (!isBipartiteUtil(i, colorArr)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isBipartiteUtil(int src, int colorArr[]) {
		colorArr[src] = 1;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(src);

		while (!q.isEmpty()) {
			int u = q.pop();
			if (arr[u][u] == 1) {
				return false;
			}

			for (int v = 0; v < arr.length; ++v) {
				if (arr[u][v] == 1 && colorArr[v] == -1) {
					colorArr[v] = 1 - colorArr[u];
					q.push(v);
				} else if (arr[u][v] == 1 && colorArr[v] == colorArr[u]) {
					return false;
				}
			}
		}
		return true;
	}
}
