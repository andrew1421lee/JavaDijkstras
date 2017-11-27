package dijkstras;

import java.util.ArrayList;

class Vertex{
    private int id;
    private int[] edges;
    private int distance;
    public Vertex(int id, int[] edges){
        this.id = id;
        this.edges = edges;
        this.distance = 999999999;
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

    public void addEdges(Edge[] e){
        for(Edge i : e){
            if(i != null
                    && !edges.contains(e)
                    && edges.stream().noneMatch(x -> x.getId() == i.getId())){
                edges.add(i);
            }
        }
    }

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
