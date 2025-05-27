import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build 
{

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  //vertex is the node of a tree 
  //graph is the tree
  public static void printShortWords(Vertex<String> vertex, int k) 
  {
    //basecase vertex and the data cannot be null
    if (vertex == null || vertex.data == null) return;

    //create a set called visited to keep track of visited vertices
    //avoid loops
    //set that contains vertex that have to contain strings
    Set<Vertex<String>> visited = new HashSet<>();
    //three arguments dfs search
    //calls the recursive version of the method
    dfsPrintShortWords(vertex, k, visited);
}

//dfs recursive helper method to graph 
private static void dfsPrintShortWords(Vertex<String> current, int k, Set<Vertex<String>> visited) 
{
  //if visited move on 
    if (visited.contains(current)) return;
    //add it to keep track of visited
    visited.add(current);

    //if the data is shorter than k
    //print 
    if (current.data.length() < k) 
    {
        System.out.println(current.data);
    }

    //visit neighbors
    //vertex of strings called neighbor
    //call the helper method again but allows us to enter all the vertexs in the neighbors list
    //for each vertext that contains a string like current.neighbors we want to assign it to neighbor 
    for (Vertex<String> neighbor : current.neighbors) 
    {
      //neighbor is now used pasesd into this method call
        dfsPrintShortWords(neighbor, k, visited);
    }
  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) 
  {
    //vertex cannot be empty or return empty str
    if (vertex == null) return "";

    //create a set of string called visited to track the vertices visited
    Set<Vertex<String>> visited = new HashSet<>();
    //dfs search
    return dfsLongest(vertex, visited);
}

//helper method to do dfs and return longest word
private static String dfsLongest(Vertex<String> current, Set<Vertex<String>> visited) 
{
  //if visited continruw on
    if (visited.contains(current)) return "";
    //track it if visited 
    visited.add(current);

    //current data/word is longest
    String longest = current.data;

    //traverse through neighbors 
    //right side is the datastructre and datatype that it contains plus a temp variable name, 
    for (Vertex<String> neighbor : current.neighbors) 
    {
      //get the longest word from the neighbor
        String candidate = dfsLongest(neighbor, visited);
        if (candidate.length() > longest.length()) 
        {
          //updated longest
            longest = candidate;
        }
    }
//return longest
    return longest;
   
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) 
  {
    //null return nothing
    if (vertex == null) return;

    //track visited nodes called visited with a set
    Set<Vertex<T>> visited = new HashSet<>();
    //dfs search
    dfsPrintSelfLoopers(vertex, visited);
}

//helper method to do dfs search to print neighbors that contains them
private static <T> void dfsPrintSelfLoopers(Vertex<T> current, Set<Vertex<T>> visited) 
{
  //if visited contains current then we have visited
    if (visited.contains(current)) return;
    //track it by adding to tracker 
    visited.add(current);

    //check if current is its neighbor
    if (current.neighbors.contains(current)) 
    {
        System.out.println(current.data);
    }

    //visit the neighbors
    for (Vertex<T> neighbor : current.neighbors) 
    {
        dfsPrintSelfLoopers(neighbor, visited);
    }
  }

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  //start from ariport
  public static boolean canReach(Airport start, Airport destination) 
  {    
    //basecase start or destination is null return false dont continue
    if (start == null || destination == null) return false;
    //if start and destination is true then true
    if (start == destination) return true;

    //set called visited to track visited airports
    Set<Airport> visited = new HashSet<>();
    //start the dfs 
    return dfsCanReach(start, destination, visited);
}

//helper method to check airports that have been reached
private static boolean dfsCanReach(Airport current, Airport target, Set<Airport> visited) 
{
  //if visited already then carry on 
  //visited contains current then false
    if (visited.contains(current)) return false;
    //add it current to the tracker
    visited.add(current);

    //current is target so we reached the target
    if (current == target) return true;

    //vistited neighbor connected airport
    for (Airport neighbor : current.getOutboundFlights()) 
    {
      //found valid reachable
        if (dfsCanReach(neighbor, target, visited)) 
        {
            return true;
        }
    }

    //not reachable 
    return false;

  }

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  //check to see if nodes in a graph is unreachable
  //<T> are generaic they can be string, Integer, airport etc
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) 
  {
    //basecase
    //set called visited to track visited nodes
     Set<T> visited = new HashSet<>();
     //dfs search
    dfsGraph(starting, graph, visited);

    //start with all nodes
    Set<T> unreachable = new HashSet<>(graph.keySet());
    //remove reachable nodes
    unreachable.removeAll(visited);
    //return the unreachable
    return unreachable;
}

//dfs helper to find reachable nodes
//<T> are generaic they can be string, Integer, airport etc
private static <T> void dfsGraph(T current, Map<T, List<T>> graph, Set<T> visited) 
{
  //skipt of visited and track it 
    if (visited.contains(current)) return;
    //if visited add it to tracked 
    visited.add(current);

    //get neighbor of current node
    List<T> neighbors = graph.get(current);

    //if no neighbors stop and dont continue
    if (neighbors == null) return;

    //recurse and visited neighbors that hasnt been visited
    for (T neighbor : neighbors) 
    {
      //recusre dfs 
        dfsGraph(neighbor, graph, visited);
    }
  }
}
