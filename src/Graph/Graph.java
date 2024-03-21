package Graph;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Graph {

	int[][] arr;
	int[] visit;
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
		visit[i] = 1;
		listView.add(i);
		for (int j = 0; j < this.arr.length; j++) {
			if(this.arr[i][j] > 0 && this.visit[j] != 1) {
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
					this.visit = new int[Integer.parseInt(k[0])];

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
}
