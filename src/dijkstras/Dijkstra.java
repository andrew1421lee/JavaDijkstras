package dijkstras;

import java.util.ArrayList;

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
                int adjindex = FindNodeIndex(graph, adj.getNumber());
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
                    possible = FindNodeIndex(Queue, adj.getNumber());
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

    private static int FindNodeIndex(ArrayList<Node> graph, int node_number){
        return graph.indexOf(graph
                .stream()
                .filter(x -> x.getNumber() == node_number)
                .findFirst()
                .orElse(null));
    }
}
