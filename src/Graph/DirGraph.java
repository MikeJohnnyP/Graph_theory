package Graph;

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
			this.arr[x][y] = 1;
			return true;
		}
		return false;
	}

}
