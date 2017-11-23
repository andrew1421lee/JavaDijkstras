package dijkstras;

import java.util.ArrayList;

public class Node {
    private int number;
    private ArrayList<AdjNode> adjacent_list = new ArrayList<>();

    public Node(int num, AdjNode ... adj){
        this.number = num;
        for(AdjNode n : adj){
            adjacent_list.add(n);
        }
    }

    public void addAdjNode(AdjNode n){
        if(n != null && !adjacent_list.contains(n)) {
            adjacent_list.add(n);
        }
    }

    public int getNumber() {
        return number;
    }

    public AdjNode[] getAdjacent() {
        return (AdjNode[]) adjacent_list.toArray();
    }
}
