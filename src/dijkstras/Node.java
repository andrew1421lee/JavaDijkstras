package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Node Class
 * Element of adjacency list implementation of graph
 * @author Anchu
 */
public class Node {
    // identifying number of a node
    private int number;
    // arraylist of adjacent nodes
    private ArrayList<AdjNode> adjacent_list = new ArrayList<>();
    // distance to node, default to maximum
    int distance = Integer.MAX_VALUE;
    // solution path

    /**
     * Node Constructor
     * Sets variables to given values
     * @param num Identifying Number
     * @param adj Adjacent Nodes
     */
    public Node(int num, AdjNode ... adj){
        this.number = num;
        adjacent_list.addAll(Arrays.asList(adj));
    }

    /**
     * addAdjNode Method
     * If node is not null and already in the list, adds given node to arraylist
     * @param n Node Object
     */
    public void addAdjNode(AdjNode n){
        if(n != null && !adjacent_list.contains(n)) {
            adjacent_list.add(n);
        }
    }

    /**
     * getNumber Method
     * returns identifying number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getAdjacent Method
     * Returns array of adjacent nodes
     * @return Adjacent Nodes
     */
    public AdjNode[] getAdjacent() {
        return adjacent_list.toArray(new AdjNode[adjacent_list.size()]);
    }

    public void setDistance(int d) { this.distance = d; }
}
