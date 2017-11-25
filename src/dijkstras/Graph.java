package dijkstras;

import java.util.ArrayList;

class Vertex{
    private int id;
    private int[] edges;
    private int distance;
    public Vertex(int id, int[] edges){
        this.id = id;
        this.edges = edges;
        this.distance = Integer.MAX_VALUE;
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

class Edge{
    private int id;
    private int cost;
    private int source;
    private int destination;
    public Edge(int id, int source, int cost, int destination){
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

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

public class Graph {
    private ArrayList<Vertex> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public boolean addVertex(Vertex v) {
        return v != null
                && !nodes.contains(v)
                && nodes.stream().noneMatch(x -> x.getId() == v.getId())
                && nodes.add(v);
    }

    public boolean addEdge(Edge e){
        return e != null
                && !edges.contains(e)
                && edges.stream().noneMatch(x -> x.getId() == e.getId())
                && edges.add(e);
    }

    public Vertex getVertex(int id){
        return nodes.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public Edge getEdge(int id){
        return edges.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
