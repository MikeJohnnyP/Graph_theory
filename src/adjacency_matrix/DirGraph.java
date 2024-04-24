package adjacency_matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirGraph extends Graph {

	@Override
	public boolean addEdge(int x, int y) {
		int maxVertex = getLength();
		if (x >= 0 && x < maxVertex && y > x && y < maxVertex) {
			this.arr[x][y] = 1;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEdge(int x, int y) {
		int maxVertex = getLength();
		if (x >= 0 && x < maxVertex && y > x && y < maxVertex) {
			this.arr[x][y] = 0;
			return true;
		}
		return false;
	}

	public int outDegree(int x) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[x][i] != 0) {
				count++;
			}
		}
		return count;
	}

	public int inDegree(int x) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][x] != 0) {
				count++;
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

	public int[][] convertUnDirGraph() {
		int[][] arrTemp = new int[arr.length][arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] != 0) {
					arrTemp[i][j] = arr[i][j];
					arrTemp[j][i] = arr[i][j];
				}
			}
		}

		return arrTemp;
	}

	public boolean isLowConected() {
		int[][] arrTemp = convertUnDirGraph();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isVisited[] = new boolean[arrTemp.length];
		queue.add(0);
		isVisited[0] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int i = 0; i < arrTemp.length; i++) {
				if (arrTemp[v][i] > 0) {
					if (!isVisited[i]) {
						queue.add(i);
						isVisited[i] = true;
					}
				}
			}
		}
		for (int k = 0; k < isVisited.length; k++) {
			if (!isVisited[k])
				return false;
		}

		return true;
	}

	@Override
	public boolean isEulerGraph() {
		if (isLowConected()) {
			int count = 0;
			for (int i = 0; i < arr.length; i++) {
				if (outDegree(i) == inDegree(i))
					count++;
			}
			return (count == arr.length);

		}
		return false;
	}

	@Override
	public boolean isConected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEulerHalfGraph() {
		if (isLowConected()) {
			int count2 = 0;
			int count = 0;
			for (int i = 0; i < arr.length; i++) {
				if (!(outDegree(i) == inDegree(i))) {
					if (inDegree(i) == outDegree(i) - 2)
						count2++;
					if (outDegree(i) == inDegree(i) - 2)
						count2++;
				} else
					count++;
			}
			return (count2 == 2 && count == arr.length - 2);
		}
		return false;

	}

	@Override
	public List<Integer> findEulerTour() {
		if (!this.isEulerGraph()) {
			System.out.println("Not euler graph!!!");
			return null;
		}
		DirGraph h = new DirGraph();
		List<Integer> c = new ArrayList<Integer>();
		h = (DirGraph) this.copyGraph();

		c.add(0);

		while (h.Edge()) {
			Integer v = null;
			for (Integer i : c) {
				if (h.outDegree(i) > 0) {
					v = i;
					break;
				}
			}
			List<Integer> sub = new ArrayList<Integer>();
			Integer e = v;
			while (h.outDegree(e) > 0) {
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
		Graph h = new DirGraph();
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
