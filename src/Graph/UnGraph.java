package Graph;

public class UnGraph extends Graph {

	@Override
	public boolean addEdge(int x, int y) {
		int maxVertex = getLength();
		if(x >= 0 && x < maxVertex && y > x && y  < maxVertex ) {
			this.arr[x][y] = 1;
			this.arr[y][x] = 1;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEdge(int x, int y) {
		int maxVertex = getLength();
		if(x >= 0 && x < maxVertex && y > x && y  < maxVertex ) {
			this.arr[x][y] = 0;
			this.arr[y][x] = 0;
			return true;
		}
		return false;
	}
	
	
	
	
	

}