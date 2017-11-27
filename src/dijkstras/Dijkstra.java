package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {

    public static void RunDijkstras(Graph graph, int start){
        // queue of nodes
        ArrayList<Vertex> Queue = new ArrayList<>();
        // new solution path
        // add all nodes to the queue
        Queue.addAll(Arrays.asList(graph.getVertices()));
        // start node is 0
        Queue.get(start).setDistance(0);
        // start with the start node
        Vertex next = Queue.remove(start);
        // loop until the queue is empty
        while(!Queue.isEmpty()){
            // comparison value distance to find min node
            int last = Integer.MAX_VALUE;
            // index of min node
            int possible = 0;
            // loop through all adjacent nodes
            for(int a : next.getEdges()){
                Edge adj = graph.getEdge(a);
                // calculate the distance to adjacent node
                int cost = adj.getCost() + next.getDistance();
                // get the graph index of the adjacent node
                //int destindex = FindNodeIndex(graph, adj.getNumber());
                // if the distance to adj node is less than what is saved in the graph for the node
                if(cost < graph.getVertex(adj.getDestination()).getDistance()){
                    // update the node's distance to cost
                    graph.getVertex(adj.getDestination()).setDistance(cost);
                    graph.getVertex(adj.getDestination()).setPrevious(next.getId());
                    // print out the result
                    System.out.println("From: "
                            + next.getId()
                            + " -> "
                            + graph.getVertex(adj.getDestination()).getId()
                            + " cost: "
                            + cost);
                }
                // For finding the min distance adjacent node
                if(graph.getVertex(adj.getDestination()).getDistance() < last && FindNodeIndex(Queue, adj.getDestination()) != -1){
                    // update the memory
                    last = cost;
                    // update the index to current
                    possible = FindNodeIndex(Queue, adj.getDestination());
                }
            }
            next = Queue.remove(possible);
            // if index becomes impossible, stop
            /*if(possible < 0){
                break;
            // otherwise remove the min distance node from the queue and continue
            }else{
                next = Queue.remove(possible);
            }*/
        }
        // print out the completed graph
        for(Vertex v : graph.getVertices()){
            System.out.println("Node: " + v.getId() + " cost: " + v.getDistance() + " previous: " + v.getPrevious());
        }
    }

    private static int FindNodeIndex(ArrayList<Vertex> graph, int node_number){
        return graph.indexOf(graph
                .stream()
                .filter(x -> x.getId() == node_number)
                .findFirst()
                .orElse(null));
    }
}
