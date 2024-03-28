package Graph;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
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
	
	public abstract boolean addEdge(int x, int y);
	public abstract boolean removeEdge(int x, int y);

	public void DFS(int i) {
		visit[i] = true;
		listView.add(i);
		for (int j = 0; j < this.arr.length; j++) {
			if(this.arr[i][j] > 0 && this.visit[j] != true) {
				DFS(j);
			}
		}
	}
	
	public void printList() {
		for(int i : this.listView) {
			System.out.print(i + " --> ");
			
		}
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
		
		while(!(queue.isEmpty())) {
			int v = queue.poll();
			for(int i = 0; i < arr.length; i++) {
				if(arr[v][i] > 0) {
					if(isVisited[i] != true ) {
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
		
		while(!(queue.isEmpty())) {
			int v = queue.poll();
			for(int i = 0; i < arr.length; i++) {
				if(arr[v][i] > 0) {
					if(isVisited[i] != true) {
						if(i == y)
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
		
		while(!(queue.isEmpty())) {
			int v = queue.poll();
			path.add(v);
			for(int i = 0; i < arr.length; i++) {
				if(arr[v][i] > 0) {
					if(isVisited[i] != true) {
						if(i == y) {
							path.add(i);
							for(int h : path) {
								System.out.print(h + " ");
							}
							return;
						}
							else {
							queue.add(i);
							isVisited[i] = true;
						}
						
					}
				}
			}
			
		}
	}
	
	public boolean checkSingleGraph() {
		for(int i = 0; i < arr.length ; i++) {
			for (int y = 0; y < arr.length; y++) {
				if(i == y && arr[i][y] != 0)
					return false;
				if(arr[i][y] > 1)
					return false;
			}
			
		}
		return true;
	}
}
