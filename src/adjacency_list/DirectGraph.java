package adjacency_list;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DirectGraph extends Graph {

	public DirectGraph() {
		super();
	}
	

	@Override
	public void addEdge(String v, String u) {
		if(!adjList.containsKey(v) || !adjList.containsKey(u)) {
				adjList.put(v,new HashSet<>());
		}else 
			adjList.get(v).add(u);	
	}

	@Override
	public void removeEdge(String v, String u) {
		 if (adjList.containsKey(v)) {
	            adjList.get(v).remove(u);
	        }		
	}
	



	@Override
	public int edges() {
		if(outAllDegree() == inAllDegree()) {
			return outAllDegree();
		}
		return 0;
	}


	@Override
	public void addVertex(String v) {
		adjList.put(v, new HashSet<String>());		
	}


	@Override
	public void removeVertex(String v) {
		adjList.remove(v);		
	}
	
	public int outDegree(String v) {
		Set<String>numOutDegree = adjList.get(v);
		if(numOutDegree==null) {
			return 0;
			
		}else {
			return numOutDegree.size();
		}
	}

	public int inDegree(String v) {
			int In = 0;
			for (Set<String>numInDegree : adjList.values()) {
				if(numInDegree.contains(v)) {
					In++;
				}
			}
			return In;
	}

	public int outAllDegree() {
		int totalOutDegree = 0;
        for (Entry<String, Set<String>> entry : adjList.entrySet()) {
            totalOutDegree += entry.getValue().size();
        }
        return totalOutDegree;
    }
	
	 public int inAllDegree() {
	        int totalInDegree = 0;
	        for (String v: adjList.keySet()) {
	            for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
	                if (entry.getValue().contains(v)) {
	                    totalInDegree++;
	                }
	            }
	        }
			return totalInDegree;
	 }
	            

	@Override
	public boolean isConnect(String v, String u ) {
		if (adjList.containsKey(v)) {
			return adjList.get(v).contains(u);
		} else {
		
	}		return false;
}



	
	
}