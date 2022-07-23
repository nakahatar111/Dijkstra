class EdgeWithWeight implements EdgeWithWeightFunctions{
  final private Integer fromVertex;
	final private Integer toVertex;
  private final Double weight;
  EdgeWithWeight(int fromVertex, int toVertex, double w){
    this.fromVertex = fromVertex;
    this.toVertex = toVertex;
    this.weight = w;
  }

  public int getFromVertex(){
    return this.fromVertex;
  }
	public int getToVertex(){
    return this.toVertex;
  }
	public double getWeight(){
    return this.weight;
  }
	public String toString(){
    return "(" + this.fromVertex + "," + this.toVertex + "," + this.weight + ")";
  }
}