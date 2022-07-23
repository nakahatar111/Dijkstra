class VertexWithWeight implements VertexWithWeightFunctions{
  private final Integer vertex;
  private Double weight;

  public VertexWithWeight(int v, double w){
    this.vertex = v;
    this.weight = w;
  }

  public double getWeight(){
    return this.weight;
  }
	public int getVertex(){
    return this.vertex;
  }
	public void setWeight(double w){
    this.weight = w;
  }
	public boolean equals(Object o){
    if( o instanceof VertexWithWeight ){
      if(((VertexWithWeight)o).getVertex() == this.vertex){
        return true;
      }
    }
    return false;
  }

	public String toString(){
    return "(" + this.vertex + "," + this.weight + ")";
  }
}