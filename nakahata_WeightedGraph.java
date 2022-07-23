class nakahata_WeightedGraph implements WeightedGraphFunctions{
  private java.util.ArrayList<Integer> vertices;
  private java.util.ArrayList<EdgeWithWeight> edges;
  
  public nakahata_WeightedGraph(){
    this.vertices = new java.util.ArrayList<Integer>();
    this.edges = new java.util.ArrayList<EdgeWithWeight>();

  }

  public Object dijkstra(int fromVertex, int toVertex, char method){

    java.util.PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = new java.util.PriorityQueue<>(vertices.size(), new VertexWithWeightWeightComparator());
    VertexWithWeight[] verticeCost = new VertexWithWeight[vertices.size()];
    int[] parent = new int[vertices.size()];
    for( int i = 0; i < vertices.size(); i++ ){
      parent[i] = -1;
      verticeCost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
    }
    int fromVertexIndex = vertices.indexOf(fromVertex);
    parent[fromVertexIndex] = 0;
    verticeCost[fromVertexIndex] = new VertexWithWeight(vertices.get(fromVertexIndex), 0.0);
    
    
    for( int i = 0; i < vertices.size(); i++ ) {
      minPriorityQueueByWeight.add(verticeCost[i]);
    }
    
    java.util.ArrayList<Integer> visited = new java.util.ArrayList<Integer>();
    while ( minPriorityQueueByWeight.size() > 0 ){
      VertexWithWeight v = minPriorityQueueByWeight.poll();
      visited.add(v.getVertex());
      int v_idx = vertices.indexOf(v.getVertex());
      for(EdgeWithWeight e : edges){
        int u_vert_index = 0;
        boolean found = false;
        if(e.getFromVertex() == v.getVertex()){
          u_vert_index = vertices.indexOf(e.getToVertex());
          if(!visited.contains(e.getToVertex())){
            found = true;
          }
        }
        if(found){
          VertexWithWeight u = verticeCost[u_vert_index];
          double compare = v.getWeight() + e.getWeight();
          if(compare < u.getWeight()){
            minPriorityQueueByWeight.remove(u);
            u.setWeight(compare);
            minPriorityQueueByWeight.add(u);
            parent[u_vert_index] = v.getVertex();
          }
        }
      }
    }
    double min_weight = 0.0;

    if(method == 'h'){
      if(parent[vertices.indexOf(toVertex)] != -1)
        return true;
      else
        return false;
    }
    if(method == 'g' || method == 'w'){
      int p = toVertex;
      java.util.ArrayList<Integer> reversePath = new java.util.ArrayList<Integer>();
      java.util.ArrayList<Integer> forwardPath = new java.util.ArrayList<Integer>();
      reversePath.add(p);
      while(p != fromVertex){
        int p_index = vertices.indexOf(p);
        if(p_index == -1)
          return false;
        p = parent[p_index];
        reversePath.add(p);
      }
      for( int i = reversePath.size()-1; i >= 0; i-- ){
        forwardPath.add(reversePath.get(i));
      }
      EdgeWithWeight[] result = new EdgeWithWeight[forwardPath.size()-1];
      for(int i = 0; i < result.length; i++){
        for(EdgeWithWeight edge_w : edges){
          if(edge_w.getFromVertex() == forwardPath.get(i) && 
          edge_w.getToVertex() == forwardPath.get(i+1)){
            result[i] = edge_w;
            min_weight+= edge_w.getWeight();
          }
          else if(edge_w.getToVertex() == forwardPath.get(i) && 
          edge_w.getFromVertex() == forwardPath.get(i+1)){
            result[i] = edge_w;
            min_weight+= edge_w.getWeight();
          }
        }
      }
      if(method == 'w')
        return min_weight;
      return result;
    }
    return true;
  }


  public boolean hasPath(int fromVertex, int toVertex){
    boolean result = false;
    Object o = dijkstra(fromVertex, toVertex, 'h');
    if( o instanceof Boolean ){
      result = (boolean)o;
    }
    return result;
  }
	public double getMinimumWeight(int fromVertex, int toVertex){ 
    Object o = dijkstra(fromVertex, toVertex, 'w');
    if( o instanceof Double ){
      return (Double)o;
    }
    return Double.NaN;
  }

	public EdgeWithWeight[] getPath(int fromVertex, int toVertex){
    Object o = dijkstra(fromVertex, toVertex, 'g');
    EdgeWithWeight[] e = new EdgeWithWeight[0];
    if( o instanceof EdgeWithWeight[] ){
      e = (EdgeWithWeight[])o;
    }
    return e;
  }

	public boolean addVertex(int v){
    return vertices.add(v);
  }
	public boolean addWeightedEdge(int from, int to, double weight){
    return edges.add(new EdgeWithWeight(from,to,weight));
  }
	public String toString(){
    StringBuilder theGraph = new StringBuilder("G = (V, E)\nV = {");
		for( int i = 0; i < vertices.size(); i++ )
		{
			theGraph.append(vertices.get(i));
			if( i < (vertices.size()-1) )
			{
				theGraph.append(",");
			}	
		}
		theGraph.append("}"); 
		theGraph.append("\nE = {");
		for( int i = 0; i < edges.size(); i++ )
		{
			EdgeWithWeight currentEdge = edges.get(i);
			theGraph.append(currentEdge.toString());
			if( i < (edges.size()-1) )
			{
				theGraph.append(",");
			}
		}
		theGraph.append("}");
		return theGraph.toString();
  }
}