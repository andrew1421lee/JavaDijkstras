package dijkstras;

import java.util.ArrayList;

/**
 * Vertex data type
 * Holds a ID, array of edges (IDs), distance, previous vertex (ID)
 */
class Vertex{
    private int id;
    private int[] edges;
    private int distance;
    private int previous;

    /**
     * Constructor
     * @param id vertex id
     * @param edges adjacent edges
     */
    public Vertex(int id, int[] edges){
        this.id = id;
        this.edges = edges;
        this.distance = 999999999;
        this.previous = -1;
    }

    // Getters and setters for object variables
    public int getPrevious(){
        return previous;
    }
    public void setPrevious(int p){
        this.previous = p;
    }
    public int getId() {
        return id;
    }
    public int[] getEdges() {
        return edges;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public int getDistance(){
        return this.distance;
    }
}

/**
 * Edge Data type
 * Contains edge ID, cost to take, source (ID), and destination (ID)
 */
class Edge{
    private int id;
    private int cost;
    private int source;
    private int destination;

    /**
     * Edge Constructor
     * @param source source vertex id
     * @param destination destination vertex id
     * @param cost cost
     * @param mode Allow duplicate edges or no
     */
    public Edge(int source, int destination, int cost, boolean mode){
        if(mode){
            if(source <= destination){
                this.id = Integer.parseInt(source + "" + destination);
            }else{
                this.id = Integer.parseInt(destination + "" + source);
            }
        }else{
            this.id = Integer.parseInt(source + "" + destination);
        }

        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    // Getters and setters for object variables
    public int getId() {
        return id;
    }
    public int getSource() {
        return source;
    }
    public int getDestination() {
        return destination;
    }
    public int getCost(){
        return cost;
    }

}

/**
 * Graph object
 * The big daddy that holds everything to make a graph
 * Contains a arraylist of NODES and an arraylist of EDGES
 */
public class Graph {
    private ArrayList<Vertex> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Add a vertex to the graph
     * Checks if the graph already has the node
     * @param v vertex
     * @return result
     */
    public boolean addVertex(Vertex v) {
        // WOWEE MORE JAVA8? CHECK OUT THAT LAMBDA
        return v != null
                && !nodes.contains(v)
                && nodes.stream().noneMatch(x -> x.getId() == v.getId())
                && nodes.add(v);
    }

    /**
     * Add an edge to the graph
     * Checks if the graph already has the edge
     * @param e edge
     * @return result
     */
    public boolean addEdge(Edge e){
        // I love Lambdas
        return e != null
                && !edges.contains(e)
                && edges.stream().noneMatch(x -> x.getId() == e.getId())
                && edges.add(e);
    }

    /**
     * Add a bunch of edges
     * @param e edge array
     */
    public void addEdges(Edge[] e){
        // Woah, lambda more like lamb-damn!
        for(Edge i : e){
            if(i != null
                    && !edges.contains(e)
                    && edges.stream().noneMatch(x -> x.getId() == i.getId())){
                edges.add(i);
            }
        }
    }

    // Getters for data in the graph
    public Vertex getVertex(int id){
        return nodes.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    public Edge getEdge(int id){
        return edges.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    public Vertex[] getVertices(){
        return nodes.toArray(new Vertex[nodes.size()]);
    }
    public Edge[] getEdges(){
        return edges.toArray(new Edge[edges.size()]);
    }
}
