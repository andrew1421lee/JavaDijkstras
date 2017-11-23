package dijkstras;


public class AdjNode{
    private int number;
    private int cost;
    public AdjNode(int num, int c){
        this.number = num;
        this.cost = c;
    }

    public int getNumber() {
        return number;
    }

    public int getCost() {
        return cost;
    }
}
