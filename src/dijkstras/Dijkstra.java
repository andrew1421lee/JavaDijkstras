package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;

class SolutionPath{
    private int start_node;
    private int total_cost;
    private AdjNode[] node_taken;

    public int getStart_node() {
        return start_node;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public AdjNode[] getNode_taken() {
        return node_taken;
    }
}

public class Dijkstra {

    public static SolutionPath RunDijkstras(ArrayList<Node> graph, int start){
        // queue of nodes
        ArrayList<Node> Queue = new ArrayList<>();
        // add all nodes to the queue
        Queue.addAll(graph);
        // start node is 0
        Queue.get(start).setDistance(0);
        Node next = Queue.remove(start);
        while(!Queue.isEmpty()){
            for(AdjNode adj : next.getAdjacent()){
                int cost = next.distance + adj.getCost();
            }
        }

        return null;
    }

    public static SolutionPath RunDijkstrasWithPriorityQueue(Node[] graph, int start){
        return null;
    }

    private static int MinDistance(ArrayList<Node> Q){
        return 0;
    }
}
