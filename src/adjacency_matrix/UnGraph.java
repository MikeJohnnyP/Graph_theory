package adjacency_matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class UnGraph extends Graph {

	@Override
	public boolean addEdge(int x, int y) {
		int maxVertex = getLength();
		if (x >= 0 && x < maxVertex && y > x && y < maxVertex) {
			this.arr[x][y] = 1;
			this.arr[y][x] = 1;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEdge(int x, int y) {
		int maxVertex = getLength();
		if (x >= 0 && x < maxVertex && y > x && y < maxVertex) {
			this.arr[x][y] = 0;
			this.arr[y][x] = 0;
			return true;
		}
		return false;
	}

	public int degree(int x) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == x && arr[i][x] != 0)
				count += 1;
			if (arr[x][i] != 0) {
				count += arr[x][i];
			}
		}
		return count;

	}

	public boolean Edge() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] > 0)
					return true;
			}
		}
		return false;

	}

	@Override
	public boolean isEulerGraph() {
		if (!this.isConected()) {
			return false;
		}

		int oddDegreeCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (degree(i) % 2 != 0) {
				oddDegreeCount++;
			}
		}

		if (oddDegreeCount == 0 || oddDegreeCount == 2) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isConected() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isVisited[] = new boolean[arr.length];
		queue.add(0);
		isVisited[0] = true;

		while (!(queue.isEmpty())) {
			int v = queue.poll();
			for (int i = 0; i < arr.length; i++) {
				if (arr[v][i] > 0) {
					if (isVisited[i] != true) {
						queue.add(i);
						isVisited[i] = true;

					}
				}
			}

		}

		for (int k = 0; k < isVisited.length; k++) {
			if (isVisited[k] == false)
				return false;
		}

		return true;

	}

	@Override
	public boolean isEulerHalfGraph() {

		return false;
	}

	@Override
	public List<Integer> findEulerTour() {
		if (!this.isEulerGraph()) {
			System.out.println("Not euler graph!!!");
			return null;
		}
		UnGraph h = new UnGraph();
		List<Integer> c = new ArrayList<Integer>();
		h = (UnGraph) this.copyGraph();

		c.add(0);

		while (h.Edge()) {
			Integer v = null;
			for (Integer i : c) {
				if (h.degree(i) > 0) {
					v = i;
					break;
				}
			}
			List<Integer> sub = new ArrayList<Integer>();
			Integer e = v;
			while (h.degree(e) > 0) {
				sub.add(e);
				for (int j = 0; j < h.arr.length; j++) {
					if (h.arr[e][j] > 0) {
						h.removeEdge(e, j);
						e = j;
						break;
					}
				}
			}
			int index = c.indexOf(v);
			c.addAll(index, sub);
		}
		return c;

	}

	@Override
	public Graph copyGraph() {
		Graph h = new UnGraph();
		h.arr = new int[getLength()][getLength()];

		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				for (int k = 0; k < arr[i][j]; k++) {
					h.addEdge(i, j);
				}
			}
		}
		return h;
	}

}
