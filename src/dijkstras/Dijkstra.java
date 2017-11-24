package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;

class SolutionPath{
    private int start_node;
    private int total_cost;
    private ArrayList<AdjNode> node_taken;

    public int getStart_node() {
        return start_node;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public AdjNode[] getNode_taken() {
        return node_taken.toArray(new AdjNode[node_taken.size()]);
    }

    public void addNode_taken(AdjNode n){
        if(n != null && !node_taken.contains(n)){
            node_taken.add(n);
        }
    }

    public void setStart_node(int start_node) {
        this.start_node = start_node;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
}

public class Dijkstra {

    public static void RunDijkstras(ArrayList<Node> graph, int start){
        // queue of nodes
        ArrayList<Node> Queue = new ArrayList<>();
        // new solution path
        // add all nodes to the queue
        Queue.addAll(graph);
        // start node is 0
        Queue.get(start).setDistance(0);
        // start with the start node
        Node next = Queue.remove(start);
        // loop until the queue is empty
        while(!Queue.isEmpty()){
            // comparison value distance to find min node
            int last = Integer.MAX_VALUE;
            // index of min node
            int possible = -1;
            // loop through all adjacent nodes
            for(AdjNode adj : next.getAdjacent()){
                // calculate the distance to adjacent node
                int cost = adj.getCost() + next.distance;
                // get the graph index of the adjacent node
                int adjindex = graph.indexOf(graph
                        .stream()
                        .filter(x -> x.getNumber() == adj.getNumber())
                        .findFirst()
                        .orElse(null));
                // if the distance to adj node is less than what is saved in the graph for the node
                if(cost < graph.get(adjindex).distance){
                    // update the node's distance to cost
                    graph.get(adjindex).setDistance(cost);
                    // print out the result
                    System.out.println("From: "
                            + next.getNumber()
                            + " -> "
                            + graph.get(adjindex).getNumber()
                            + " cost: "
                            + cost);
                }
                // For finding the min distance adjacent node
                if(graph.get(adjindex).distance < last){
                    // update the memory
                    last = cost;
                    // update the index to current
                    possible = Queue.indexOf(graph
                            .stream()
                            .filter(x -> x.getNumber() == adj.getNumber())
                            .findFirst()
                            .orElse(null));

                }
            }
            // if index becomes impossible, stop
            if(possible < 0){
                break;
            // otherwise remove the min distance node from the queue and continue
            }else{
                next = Queue.remove(possible);
            }
        }
        // print out the completed graph
        for(Node n : graph){
            System.out.println("Node: " + n.getNumber() + " cost: " + n.distance);
        }
    }

    public static SolutionPath RunDijkstrasWithPriorityQueue(Node[] graph, int start){
        return null;
    }
}
