import java.util.ArrayList;
import java.util.List;

public class Vertex<T> 
{
  //data is data in teh vertex
  T data;
  //neighbors is a list of vertexs that come after the current vertexes 
  List<Vertex<T>> neighbors;

  public Vertex(T data) {
    this(data, new ArrayList<>());
  }

  public Vertex(T data, List<Vertex<T>> neighbors) 
  {
    this.data = data;
    this.neighbors = neighbors;
  }
}