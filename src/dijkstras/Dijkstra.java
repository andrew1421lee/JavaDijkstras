package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dijkstras Class
 * Holds all methods Dijkstras
 */
public class Dijkstra {

    /**
     * RunDijkstras
     * Runs Dijkstras algorithm on the given information
     * @param graph graph
     * @param start start vertex
     * @param delay delay
     * @return completed graph
     */
    public static Graph RunDijkstras(Graph graph, int start, int delay){
        // queue of nodes
        ArrayList<Vertex> Queue = new ArrayList<>();
        DelayPrint("Create Queue data structure", delay);
        // add all nodes to the queue
        Queue.addAll(Arrays.asList(graph.getVertices()));
        DelayPrint("Add all vertices to Queue", delay);
        // start node is 0
        Queue.get(start).setDistance(0);
        DelayPrint("Set start vertex distance to 0", delay);
        // start with the start node
        Vertex next = Queue.remove(start);
        DelayPrint("Entering while loop", delay);
        // loop until the queue is empty
        while(!Queue.isEmpty()){
            // comparison value distance to find min node
            int last = Integer.MAX_VALUE;
            // index of min node
            int possible = 0;
            DelayPrint("Taking Vertex " + next.getId() + " off the Queue", delay);
            // loop through all adjacent nodes
            DelayPrint("Entering adjacent edge loop", delay);
            for(int a : next.getEdges()){
                Edge adj = graph.getEdge(a);
                // calculate the distance to adjacent node
                int cost = adj.getCost() + next.getDistance();
                DelayPrint("Calculating potential cost for: " + next.getId() + "->" + adj.getDestination() + " = " + cost, delay);
                // get the graph index of the adjacent node
                //int destindex = FindNodeIndex(graph, adj.getNumber());
                // if the distance to adj node is less than what is saved in the graph for the node
                if(cost < graph.getVertex(adj.getDestination()).getDistance()){
                    DelayPrint("Potential cost is less than previous distance: " + cost + " < " + graph.getVertex(adj.getDestination()).getDistance(), delay);
                    // update the node's distance to cost
                    graph.getVertex(adj.getDestination()).setDistance(cost);
                    DelayPrint("Update destination distance to take Edge", delay);
                    graph.getVertex(adj.getDestination()).setPrevious(next.getId());
                    DelayPrint("Update destination previous vertex", delay);
                }
                // For finding the min distance adjacent node
                if(graph.getVertex(adj.getDestination()).getDistance() < last && FindNodeIndex(Queue, adj.getDestination()) != -1){
                    // update the memory
                    last = cost;
                    // update the index to current
                    possible = FindNodeIndex(Queue, adj.getDestination());
                }
            }
            DelayPrint("Get the minimum distance vertex", delay);
            // pop off the min distance vertex
            next = Queue.remove(possible);
        }
        DelayPrint("Return completed graph", delay);
        // print out the completed graph
        for(Vertex v : graph.getVertices()){
            System.out.println("Node: " + v.getId() + " cost: " + v.getDistance() + " previous: " + v.getPrevious());
        }
        return graph;
    }

    /**
     * Helper function to find a node based on it's node number
     * @param graph data to search
     * @param node_number number to find
     * @return index in data
     */
    private static int FindNodeIndex(ArrayList<Vertex> graph, int node_number){
        // wow check out those JAVA 8 FEATURES! +extra credit
        return graph.indexOf(graph
                .stream()
                .filter(x -> x.getId() == node_number)
                .findFirst()
                .orElse(null));
    }

    /**
     * Helper function to print out with a delay
     * @param out output
     * @param delay time
     */
    private static void DelayPrint(String out, int delay){
        // print it out
        System.out.println(out);
        // sleep
        try{
            Thread.sleep(delay);
        }catch(InterruptedException ex){
            System.out.println("[ERROR] Sleep Interrupted");
        }

    }
}
